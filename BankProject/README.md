# How to build & run the app
Import the `BankProject` folder into Eclipse IDE as a new project. Then open the file where the UI is instantiated (in `src/com/ui/AppWindow.java`), 
and run it by clicking the green 'Run' button in Eclipse.
![Running app](https://i.imgur.com/hqWl5mX.png)

# How to package the app
To package the app as a JAR file (e.g. for release), export it as a runnable JAR file (right-click the project
in Project Explorer, select "Export", and follow steps to export it as a runnable JAR with required libraries extracted into it).
![Export as runnable JAR](https://i.imgur.com/CKcM3SH.png)

(On Linux, you may have to set the JAR file's permissions to executable to make it launchable on double-click.)

**NOTE:** The JAR file has to be in the same folder as the DB to work properly (it's not possible to pack an editable DB inside the JAR itself),
as dictated by the DB path specified by the DB Connection code (in `src/com/util/DBUtil.java`).
Keep that in mind when deciding the export destination and how to package the app for release.

# Miscellaneous
- You can reset the DB by running the schema on it with an SQLite client (e.g. sqlite3). You could also do it manually in the GUI by deleting all
records one by one.
