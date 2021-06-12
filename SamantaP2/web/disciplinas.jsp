<%-- 
    Document   : disciplinas
    Created on : 12 de jun. de 2021, 15:43:37
    Author     : MARCOSESTEVAODASILVA
--%>

<%@page import="db.Disciplinas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String requestError = null;
    if(request.getParameter("add")!=null){
        String nome = request.getParameter("nome");
        String diaDaSemana = request.getParameter("diadasemana");
        String horario = request.getParameter("horario");
        int qtAulas = Integer.parseInt(request.getParameter("qtaulas"));
        try{
            Disciplinas.addDisciplina(nome, diaDaSemana, horario, qtAulas, 0.0, 0.0);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestError = "Falha na criação do disciplina ["+ex.getMessage()+"]";
        }
    }else if(request.getParameter("remove")!=null){
        String nome = request.getParameter("nome");
        try{
            Disciplinas.removeDisciplina(nome);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestError = "Falha na exclusão do disciplina ["+ex.getMessage()+"]";
        }
    }else if(request.getParameter("update")!=null){
        String nome = request.getParameter("nome");
        Double notap1 = Double.parseDouble(request.getParameter("notap1"));
        Double notap2 = Double.parseDouble(request.getParameter("notap2"));
        try{
            Disciplinas.updateDisciplina(nome, notap1, notap2);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestError = "Falha na alteração do disciplina ["+ex.getMessage()+"]";
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias - MyFinance$</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <%if(requestError != null){%>
            <div style="color: red">
                Erro ao manipular usuário: <%= requestError %>
            </div>
        <%}%>
        <h2>Disciplinas</h2>
        <% String login = (String) session.getAttribute("user.login");%>
        <%if(login == null){%>
            <div>Esta página é restrita a usuários logados.</div>
        <%}else{%>
        <fieldset>
            <legend>Nova disciplinaegoria:</legend>
            <form method="post">
                Nome: <br/><input type="text" name="nome"/>
                <br/>Dia da semana <br/><input type="text" name="diadasemana"/>
                <br/>Horário:<br/><input type="text" name="horario"/>
                <br/>Quantidade de aulas: <br/><input type="text" name="qtaulas"/>
                <br/><br/>
                <input type="submit" name="add" value="Adicionar"/>
            </form>
        </fieldset>
        <br/>
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Dia da semana</th>
                <th>Horário</th>
                <th>Qt. Aulas</th>
                <th>Nota P1</th>
                <th>Nota P2</th>
                <th>Descrição</th>
                <th>Exclusão</th>
            </tr>
            <%for(Disciplinas disciplina: Disciplinas.getDisciplinas()){%>
                <tr>
                    <td><%= disciplina.getNome()%></td>
                    <td><%= disciplina.getDiaDaSemana()%></td>
                    <td><%= disciplina.getHorario()%></td>
                    <td><%= disciplina.getQtAulas()%></td>
                    <td><%= disciplina.getNotap1()%></td>
                    <td><%= disciplina.getNotap2()%></td>
                    <td>
                        <form method="post">
                            <input type="hidden" name="nome" value="<%= disciplina.getNome()%>"/>
                            <input type="text" name="notap1" />
                            <input type="text" name="notap2" />
                            <input type="submit" name="update" value="Alterar"/>
                        </form>
                    </td>
                    <td>
                        <form method="post">
                            <input type="hidden" name="nome" value="<%= disciplina.getNome()%>"/>
                            <input type="submit" name="remove" value="Remover"/>
                        </form>
                    </td>
                </tr>
            <%}%>
        </table>
        <%}%>
    </body>
</html>