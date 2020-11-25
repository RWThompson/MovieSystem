# Movie Catalog System
## Getting Started
To get started using this system follow these steps:

1. Clone the repository to a local folder
2. Open MySQL Workbench so that we can import the database
3. Connect to you local instance in MySQL Workbench
3. Import the database to MySQL, you can do this with the included dump file or by using the script in the txt file; to import using the dump file follow step 4, to import using the script follow step 5
4. Go to Server, then "Data Import", then click the 3 dots on the right of the "Import from Self-Contained File" option, find the dump file, select it then click open
5. Copy and paste the MySQL script into the query window and then click the lightning bolt above the window (third icon from the left)
6. Using a bash terminal like Git Bash run the following command from within the local folder that you cloned the repository to `mvn spring-boot:run`

## Useful Information
### Request Mappings and Parameters
| URL | Params |
| :--- | ---: |
| /create/movie | title |
| /update/movie | id, updatedTitle |
| /delete/movie | id |
| /read/movie/bydirector | director |
| /read/movie/aboverating | rating |
| /read/movie/all |  |
| /read/movie/bytitle | title |
| /read/movie/byid | id |
| /create/director | fullname |
| /update/director | id, updatedName |
| /delete/director | id |
| /read/director/all |  |
| /read/director/byname | fullname |
| /read/director/byid | id |
| /create/rating | rating, movieID |
| /update/rating | id, updatedRating |
| /delete/rating | id |
| /read/rating/all |  |
| /read/rating/bymovieID | movieID |
| /read/rating/byid | id |
| /create/moviedirector | movieID, directorID |
| /update/moviedirector/director | id, directorID |
| /update/moviedirector/movie | id, movieID |
| /delete/moviedirector | id |
| /read/moviedirector/all |  |
| /read/moviedirector/byid | id |