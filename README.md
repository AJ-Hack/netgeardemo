# Requirements

docker-compose (setup for linux containers)
curl (or equivalent, for endpoint testing)

# Getting Started

### Clone project
```
git clone https://github.com/AJ-Hack/netgeardemo.git
cd netgeardemo
```

### Run the app in docker
```
docker-compose up --build
```

### Endpoints
| Method | Endpoint Path    | Action | Required Request Body Fields                                 |
|--------|------------------|--------|--------------------------------------------------------------|
POST | ~/api/books      | Create a book | N/A                                                          |
GET | ~/api/books      | Get a paginated list of books | {"author": String, "title": String, "publishedDate": String} |
GET | ~/api/books/{id} | Get a book with a specific ID | N/A |
DELETE | ~/api/books/{id} | Delete a book with a specific ID | N/A |


# Running unit tests
```
./gradlew build test
```

# Manual testing using curl

### Create a book
```
curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d '{"title":"Effective Java","author":"Joshua Bloch","publishedDate":"2023-05-15T12:00:00Z","genre":"Programming"}'
```

### Get all books (paginated)
```
curl "http://localhost:8080/api/books?page=0&size=5"
```

### Get single book
```
curl http://localhost:8080/api/books/1
```

### Soft delete
```
curl -X DELETE http://localhost:8080/api/books/1
```