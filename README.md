# Books to Scrape - Reverse Engineered API

## Overview

This project reverse-engineers the publicly accessible catalog of Books to Scrape and exposes REST APIs for accessing book data.

The source website does not provide an official public developer API for its catalog, so this project uses its publicly accessible HTML pages as the data source.

The application is built using Java, Spring Boot, Maven, and Jsoup.

## Tech Stack

- Java 21
- Spring Boot
- Maven
- Jsoup
- REST API
- JUnit 5
- Git / GitHub

## Features

- Scrape book information from the public website
- Automatically discover book URLs across catalog pages
- Get a book by ID
- Paginate through books
- Search books by title
- Handle invalid book IDs
- Basic automated testing

## Book Data

The API extracts:

- Title
- Price
- Availability
- Rating

## API Endpoints

### Health Check

```text
GET /hello