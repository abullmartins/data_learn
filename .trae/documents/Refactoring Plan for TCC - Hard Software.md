# TCC - Hard Software - Refactoring Plan

## Overview
The project has been reorganized into a standard Maven project structure. This improves maintainability, build management, and follows Java best practices.

## Changes Implemented

### 1. Directory Structure
- Moved source code to `src/main/java`.
- Moved resources (images, configs) to `src/main/resources`.
- Removed IDE-specific files (NetBeans, Eclipse) and the old build folder.
- Created `pom.xml` for dependency management.

### 2. Infrastructure
- **ConnectionFactory**: Created `br.com.hardsoftware.mercearia.infra.ConnectionFactory` to handle database connections. It reads configuration from `src/main/resources/database.properties`.
- **Database Configuration**: Created `database.properties` to store DB credentials (no longer hardcoded in Java).
- **Conexao**: Refactored `Conexao.java` to use `ConnectionFactory`. Note: usage of `Conexao.getConexao()` is deprecated in favor of direct `ConnectionFactory` usage.

### 3. Code Refactoring
- **DAOs**: Started refactoring DAOs to remove static connections (which cause issues in multi-threaded environments or if connection drops).
    - `LoginDao`, `CidadeDao`, `EstadoDao` have been updated to use `try-with-resources` and get a fresh connection for each operation.
- **Models**:
    - `Cliente.java`: Extracted CPF validation logic to `br.com.hardsoftware.mercearia.util.Validator`.
    - Cleaned up formatting and setters.

## Next Steps (for the developer)

1.  **Continue DAO Refactoring**:
    - Update remaining DAOs (e.g., `ProdutoDao`, `VendaDao`) to follow the pattern used in `LoginDao`.
    - Remove `static Connection con` fields.
    - Use `try (Connection con = ConnectionFactory.getConnection()) { ... }`.

2.  **Controller Refactoring**:
    - Controllers like `LoginControle` use static state (`funcOnline`). Consider moving this to a Session context or passing the user object around.

3.  **Database**:
    - Ensure your MySQL database is running and import the SQL dump if you haven't (found in `Documentos/BD`).
    - Update `src/main/resources/database.properties` with your actual DB password.

4.  **Running**:
    - Use Maven to build: `mvn clean install`.
    - Run the main class: `br.com.hardsoftware.mercearia.view.TelaLogin` (or the appropriate entry point).

## Dependencies
- **MySQL Connector**: 8.0.28
- **JUnit**: 4.13.2 (for testing)
