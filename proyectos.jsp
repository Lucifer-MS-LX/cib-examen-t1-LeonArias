<%@page import="entidades.Proyecto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Proyectos de Inversión Pública</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f5f6f7;
        }
        .card {
            border-radius: 10px;
        }
    </style>
</head>
<body>

<div class="container-fluid mt-5 px-4">
    <div class="row g-4">

        <!-- FORMULARIO -->
        <div class="col-lg-4">
            <div class="card p-4 shadow-sm">
                <h4 class="mb-3">Registrar Proyecto</h4>

                <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
                %>
                    <div class="alert alert-danger" role="alert"><%= error %></div>
                <%
                }
                %>

                <form method="post" action="ProyectoServlet">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre del Proyecto</label>
                        <input id="nombre" name="nombre" type="text" class="form-control"
                               minlength="4" maxlength="60" required>
                    </div>

                    <div class="mb-3">
                        <label for="descripcionTecnica" class="form-label">Descripción técnica del Proyecto (Opcional)</label>
                        <textarea id="descripcionTecnica" name="descripcionTecnica"
                                  class="form-control" rows="3"></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="fechaInicio" class="form-label">Fecha de inicio del Proyecto</label>
                        <input id="fechaInicio" name="fechaInicio" type="date"
                               class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="fechaCulminacion" class="form-label">Fecha de culminación del Proyecto</label>
                        <input id="fechaCulminacion" name="fechaCulminacion" type="date"
                               class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="asesorTecnico" class="form-label">Asesor Técnico del Proyecto (Nombres y Apellidos)</label>
                        <input id="asesorTecnico" name="asesorTecnico" type="text"
                               class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="presupuesto" class="form-label">Presupuesto Asignado</label>
                        <input id="presupuesto" name="presupuesto" type="number"
                               step="0.01" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Registrar</button>
                    <a href="ProyectoServlet" class="btn btn-warning">Listar</a>
                </form>
            </div>
        </div>

        <!-- TABLA -->
        <div class="col-lg-8">
            <div class="card p-4 shadow-sm">
                <h4 class="mb-3">Proyectos Disponibles</h4>

                <div class="table-responsive">
                    <table class="table table-bordered table-striped table-hover align-middle">
                        <thead class="table-light">
                            <tr>
                                <th>ID</th>
                                <th>Nombre del Proyecto</th>
                                <th>Descripción técnica</th>
                                <th class="text-nowrap">Inicio</th>
                                <th class="text-nowrap">Fin</th>
                                <th>Asesor Técnico</th>
                                <th class="text-end">Presupuesto</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                        List<Proyecto> lista = (List<Proyecto>) request.getAttribute("proyectos");
                        if (lista != null && !lista.isEmpty()) {
                            for (Proyecto proyecto : lista) {
                        %>
                            <tr>
                                <td><%= proyecto.getId() %></td>
                                <td><%= proyecto.getNombre() %></td>
                                <td><%= proyecto.getDescripcionTecnica() == null ? "" : proyecto.getDescripcionTecnica() %></td>
                                <td class="text-nowrap"><%= proyecto.getFechaInicio() %></td>
                                <td class="text-nowrap"><%= proyecto.getFechaCulminacion() %></td>
                                <td><%= proyecto.getAsesorTecnico() %></td>
                                <td class="text-nowrap text-end"><%= String.format(Locale.US, "S/ %,.2f", proyecto.getPresupuesto()) %></td>
                            </tr>
                        <%
                            }
                        } else {
                        %>
                            <tr>
                                <td colspan="7" class="text-center text-muted">No hay proyectos registrados.</td>
                            </tr>
                        <%
                        }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
