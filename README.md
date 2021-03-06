## Interview Problem

### Fix Compilation errors

Some fool engineer checked in code that doesn't work as they had to
leave early for the Fraggle Rock marathon on HBO. Start by fixing up
the broken code.

### Implement the REST APIs

Fill out the TODO(s) inside the REST api endpoints

### Endpoints

#### AlbumResource.java (port 8080)

##### GET /albumn/{albumn_name}

Retrieve the details about a given album.

##### POST /album

Create a new album.

---

#### GroupResource.java (port 8080)

##### GET /group/{group_name}

Retrieve the details about a given group.

##### POST /group

Create a new group.

---

#### MusicResource.java (port 8080)

##### GET /music

Return all known music artists and their albumns

##### GET /music?group={group_name}

Return all known music for a particular artist

---

#### MySqlHealthCheck.java (port 8081)

##### GET /healthcheck

Returns a health/unhealthy for the mysql instance.

---

### Compilation (from root directory)

```bash
mvn clean compile package
```

### Local Deployment (from root directory)

```bash
cd tools\local-dev
docker-compose up
```

### Local Redeployment (from root directory)

```bash
cd tools\local-dev
python3 redeploy.py
```

### Entry Point

##### MusicApplication.java

Entry point for loading the configuration details, creating and
registering any REST endpoint

### Example Queries

```
curl -v -X POST localhost:8080/group -H 'Content-Type: application/json' -d '{
    "groupName": "Foo Fighters",
    "yearFormed": 1994,
    "currentMembers": [
        "Dave Grohl",
        "Nate Mendel",
        "Pat Smear",
        "Taylor Hawkins",
        "Chris Sheflett"
    ],
    "pastMembers": [
        "William Goldsmith",
        "Franz Stahl"
    ]
}'

curl -v -X GET localhost:8080/group/Rm9vIEZpZ2h0ZXJz

curl -v -X POST localhost:8080/album -H 'Content-Type: application/json' -d '{
    "artistName" : "Foo Fighters",
    "albumName" : "The Colour and the Shape",
    "releaseYear" : 1997
}'

curl -v -X GET localhost:8080/album/VGhlIENvbG91ciBhbmQgdGhlIFNoYXBl

curl -v -X GET localhost:8080/music

curl -v -X GET localhost:8080/music?group=Rm9vIEZpZ2h0ZXJz
```