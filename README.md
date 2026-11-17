# Proyectos de Inversión Pública

Ejercicio Java Web con JSP, Servlets, Bootstrap y MySQL para registrar y listar proyectos de inversión pública.

## Requisitos

- Java 21 o posterior y Tomcat 10.1.
- IntelliJ IDEA con Smart Tomcat, o Eclipse IDE for Enterprise Java and Web Developers.
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

## Abrir y ejecutar en Eclipse

Esta carpeta no incluye los metadatos `.project` y `.classpath` de Eclipse. Para ejecutarla sin depender de IntelliJ:

1. En Eclipse, crea un **Dynamic Web Project** llamado `proyectos`, con Java 21 o posterior y Tomcat 10.1 como **Target runtime**. Usa `proyectos` como **Context root** y las carpetas estándar `src` y `WebContent`.
2. Copia `proyectos.jsp` a la raíz de `WebContent`.
3. Copia `WEB-INF/web.xml` a `WebContent/WEB-INF/web.xml` y el JAR de `WEB-INF/lib` a `WebContent/WEB-INF/lib`.
4. Copia los tres fuentes Java de `WEB-INF/classes/conexion`, `WEB-INF/classes/entidades` y `WEB-INF/classes/servlet` a las mismas carpetas de paquetes dentro de `src`. Eclipse los compilará en `WebContent/WEB-INF/classes`.
5. Haz clic derecho en el proyecto y selecciona **Run As > Run on Server** con Tomcat 10.1. Abre `http://localhost:8080/proyectos/ProyectoServlet` (ajusta el puerto si tu Tomcat usa otro).

Ejecuta primero `database.sql` en MySQL Workbench, igual que para IntelliJ. Entra por `/ProyectoServlet` para cargar el listado desde MySQL.
