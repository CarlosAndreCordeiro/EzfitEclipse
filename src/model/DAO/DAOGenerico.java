/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;

/**
 *
 * @author wemerson
 */
public interface DAOGenerico<E> {
    
    public void adiciona(E e);
    public void alterar(E e);
    public E recuperar(int codigo);
    public void deletar(E e);
    public List<E> recuperarTodos();
    
}
