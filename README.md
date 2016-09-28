# mysql-index-api
Testing SQL index's performance with researches in the database.

#Motivation

With the given **resources** folder, we must try to persist its files' content 10k and 100k times in the database. Needs to be done in a table without an index, and in a table with an index.

After persisting data in the db, we must compare the time a query can be faster than another, if a table has an index.

#Resources

- The database creation is in the `DbModel.sql` file;
- The files used to insert data are in the `resources` folder. 
- The parsing pattern is:

```
0,yyyy-MM-dd hh:mm:ss,12.34566,13.45678
```
