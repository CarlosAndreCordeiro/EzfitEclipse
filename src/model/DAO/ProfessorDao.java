/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.DAO;

import model.Professor;

/**
 *
 * @author $Carlos Cordeiro <carloscordeiroconsultor@gmail.com>
 */
public interface ProfessorDao extends DAOGenerico<Professor>{
  
    public boolean logarProfessor(String login,String senha); 
    public Professor recuperarCpf(String cpf);
}
