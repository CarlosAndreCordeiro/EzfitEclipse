/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.Exercicio;

/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */

public interface ExercicioDao extends DAOGenerico<Exercicio>{
   
    public Exercicio recuperarNome(String nome);
}
