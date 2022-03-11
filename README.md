<p align="center">
  <img src='/src/main/webapp/img/logo.png' alt='logo'/>
</p>

# Sneakers Web - Website E-Commerce for sneakers ([_Preview_](/Preview.md))
## 1. About project
This project are built for research and study.
Technology used:
- **Frontend:** Java (JSP/Servlet + Apache Tomcat 9.0)
- **Backend:** SQL Server 2019
## 2. Collaborator
- [Thái Thành Nam](https://github.com/thanhnam2811) (me)
- [Cao Hoài Tấn](https://github.com/caohoaitan)
## 3. How to install
### Prerequisites:
- **[Java 16](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html)**
- **[Apache Tomcat 9](https://tomcat.apache.org/download-90.cgi)**
- **[SQL Server 2019](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)**
- **[SQL Server Management Studio](https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver15)**
- **[IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)**
### Step 1: Clone project and open with IntelliJ
- From [SneakersWeb Project](https://github.com/thanhnam2811/SneakersWeb), choose `Code_ -> _Download ZIP`. ([Others method](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository))
- Extract _that file_ and put in any folder.
- Right click on folder _SneakersWeb-master_ and choose _Open Folder as IntelliJ IDEA Project_ (or open IntelliJ and choose `File -> Open.. -> PathToFolder/FashioWeb-master`).
### Step 2: Run script SQL
- Open: `.\src\main\webapp\WEB-INF\scriptSQL`, you will see files:
  1. `1_create_db.sql`: Create database and tables.
  2. `2_create_triggers.sql`: Create triggers.
  3. `3_insert_data.sql`: Insert data.
  4. `4_create_procedures.sql`: Create procedures.
- Run that file sequentially with SSMS and you wil have DataBase: **db_sneakers**
- For someone who don't know [How to run sql file with SSMS](https://stackoverflow.com/a/28794066)
### Step 3: Config your Tomcat Server
- In IntelliJ, choose: `File -> Settings... -> Build, Execution, Deployment -> Application Servers`
- Click button `Add` (+), choose Tomcat Server.
- Put your `Path to Tomcat` (normally it is: `C:\Program Files\Apache Software Foundation\Tomcat 9.0`) in Tomcat Home
- Name your server (Optionally)
- Click `Apply`, then click `OK`
- On top-right corner, click `Add Configuaration.. -> Add (+) -> Tomcat Server -> Local`
- Change tab `Deployment`, click `Add (+) -> Artifacts..`
- Change port _if you are already use this port_.
- Click `Apply`, then click `OK`
### Step 4: Run website
- Click run button or `Shift + F10` to run website
### Additional
- Default username and password (username | password)
    - _Admins:_
        - Admin (admin | adminadminadmin)
    - _Users:_
        - Trung (trung | trungtrungtrung)
        - Toàn (toan | toantoantoan)
        - Nam (nam | namnamnam)
        - Tấn (tan | tantantan)
## 4. Copyright Information
- Template user web: [**Karma Shop | Colorlib**](https://colorlib.com/wp/template/karma/)
- Template admin web: [**SB Admin 2 | StartBootstrap**](https://github.com/StartBootstrap/startbootstrap-sb-admin-2)
