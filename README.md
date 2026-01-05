# Mercearia Hard Software

## Project Overview
This project has been reorganized into a standard Maven project structure to follow Java best practices. It includes a Swing-based GUI for managing a grocery store (Mercearia).

## Structure
- `src/main/java`: Java source code.
- `src/main/resources`: Configuration files (database.properties) and resources (images).
- `pom.xml`: Maven build configuration.

## Configuration
The database connection is now configured in `src/main/resources/database.properties`.
Please update the password field to match your local MySQL installation:
```properties
db.url=jdbc:mysql://localhost:3306/bdmercado
db.user=root
db.password=YOUR_PASSWORD_HERE
db.driver=com.mysql.cj.jdbc.Driver
```

## Database
The database schema can be found in `Documentos/BD/bdmercado.sql` (if available in the original backup). Ensure the database `bdmercado` exists and tables are created.

## Key Changes & Improvements
1.  **Dependency Management**: Switched to Maven.
2.  **Connection Pooling**: Introduced `ConnectionFactory` to handle DB connections securely and efficiently.
3.  **Refactoring**:
    - `Conexao.java` now delegates to `ConnectionFactory`.
    - `LoginDao`, `CidadeDao`, `EstadoDao` have been refactored to use `try-with-resources` to prevent connection leaks.
    - `Cliente.java` model validation logic has been moved to `br.com.hardsoftware.mercearia.util.Validator`.

## Next Steps
To fully modernize the application:
1.  **Refactor remaining DAOs**: Update `ClienteDao`, `ProdutoDao`, etc., to remove `static Connection con` and use `ConnectionFactory.getConnection()` inside each method (similar to `LoginDao`).
2.  **Remove Swing logic from DAOs**: Some DAOs still use `JOptionPane`. Exceptions should be thrown and handled in the Controller or View layer.
3.  **Security**: Use BCrypt for password hashing instead of plain text or MD5.

## How to Run
Using Maven:
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="br.com.hardsoftware.mercearia.view.TelaLogin"
```
(Note: You may need to adjust the main class depending on the entry point).
