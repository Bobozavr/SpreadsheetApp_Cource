param([string]$JdkHome = $env:JAVA_HOME)
$ErrorActionPreference = 'Stop'
$projectRoot = $PSScriptRoot
if (-not $JdkHome) {
    $compiler = Get-Command javac -ErrorAction SilentlyContinue
    if ($compiler) { $JdkHome = Split-Path (Split-Path $compiler.Source) }
}
if (-not $JdkHome -or -not (Test-Path -LiteralPath (Join-Path $JdkHome 'bin/javac.exe'))) {
    throw 'JDK 17 or newer is required. Run: .\build.ps1 -JdkHome "C:\path\to\jdk"'
}
$classes = Join-Path $projectRoot 'build/classes'
if (Test-Path -LiteralPath $classes) {
    $resolvedClasses = (Resolve-Path -LiteralPath $classes).Path
    $expectedClasses = [System.IO.Path]::GetFullPath((Join-Path $projectRoot 'build/classes'))
    if ($resolvedClasses -ne $expectedClasses) { throw 'Unexpected build path.' }
    Remove-Item -LiteralPath $resolvedClasses -Recurse -Force
}
New-Item -ItemType Directory -Path $classes,(Join-Path $projectRoot 'dist') -Force | Out-Null
$sources = Get-ChildItem -LiteralPath (Join-Path $projectRoot 'src/main/java') -Recurse -Filter '*.java' | Select-Object -ExpandProperty FullName
& (Join-Path $JdkHome 'bin/javac.exe') --release 17 -encoding UTF-8 -d $classes $sources
if ($LASTEXITCODE -ne 0) { throw 'Compilation failed.' }
$jarPath = Join-Path $projectRoot 'dist/SpreadsheetApp.jar'
& (Join-Path $JdkHome 'bin/jar.exe') "-J-Djava.io.tmpdir=$projectRoot/build" --create --file $jarPath --main-class spreadsheet.app.Main -C $classes .
if ($LASTEXITCODE -ne 0) { throw 'JAR packaging failed.' }
Write-Host "Built: $jarPath"
Write-Host 'Run: java -jar dist/SpreadsheetApp.jar'
