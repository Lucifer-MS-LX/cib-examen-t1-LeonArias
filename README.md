# Proyectos de Inversión Pública

Ejercicio Java Web con JSP, Servlets, Bootstrap y MySQL para registrar y listar proyectos de inversión pública.

## Requisitos

- Java 21 o posterior, IntelliJ IDEA y Tomcat 10.1.
- MySQL local en `localhost:3306`.
- El driver MySQL Connector/J 8.4.0 ya está en `WEB-INF/lib`.

## Preparar la base de datos

1. Inicia MySQL.
2. Abre `database.sql` en MySQL Workbench con una cuenta que pueda crear bases de datos y usuarios.
3. Ejecuta el script completo. Crea la base de datos `proyectos_inversion_publica`, la tabla `proyectos` y el usuario local de demostración `proyectos_app` con permisos `SELECT` e `INSERT`.

La contraseña de demostración está en database.sql y ConexionMySQL.java. No se necesitan variables de entorno para este ejercicio local.

## Abrir y ejecutar en IntelliJ

1. En IntelliJ, usa **File > Open** y selecciona la carpeta `cib-examen-t1-LeonArias`.
2. En **File > Project Structure**, selecciona un JDK 21 o posterior. Marca `WEB-INF/classes` como **Sources Root** y agrega los archivos `servlet-api.jar` y `jsp-api.jar` de la carpeta `lib` de Tomcat 10.1 como bibliotecas del módulo.
3. Desde la terminal integrada, en la raíz del proyecto, compila las clases para que Tomcat pueda cargarlas desde `WEB-INF/classes`:

   ```powershell
   $tomcatHome = 'C:\ruta\a\apache-tomcat-10.1.x'
   javac -encoding UTF-8 -cp "$tomcatHome\lib\servlet-api.jar" -d 'WEB-INF\classes' 'WEB-INF\classes\entidades\Proyecto.java' 'WEB-INF\classes\conexion\ConexionMySQL.java' 'WEB-INF\classes\servlet\ProyectoServlet.java'
   ```

4. En **Run > Edit Configurations**, crea una configuración **Smart Tomcat** como en `Semana02-Viernes`: indica la carpeta de Tomcat 10.1, usa esta carpeta del proyecto como **Deployment Directory** y establece el **Context Path** en `/proyectos` y el puerto HTTP en `8080`.
5. Inicia Tomcat con esa configuración y abre `http://localhost:8080/proyectos/ProyectoServlet` en el navegador.

Si cambias archivos `.java`, repite la compilación y reinicia la aplicación. El formulario registra por POST; el botón **Listar** y la redirección después del registro abren el GET del Servlet.
