@echo off

echo ========================================
echo Books API Test Script
echo ========================================

echo.
echo [1] Testing health endpoint...
curl -f http://localhost:8080/hello

echo.
echo.
echo [2] Testing get book by ID...
curl -f http://localhost:8080/api/books/1

echo.
echo.
echo [3] Testing pagination...
curl -f "http://localhost:8080/api/books?page=2"

echo.
echo.
echo [4] Testing search...
curl -f "http://localhost:8080/api/search?title=light"

echo.
echo.
echo [5] Testing invalid book ID...
curl -f http://localhost:8080/api/books/9999

echo.
echo.
echo ========================================
echo API tests completed.
echo ========================================

pause