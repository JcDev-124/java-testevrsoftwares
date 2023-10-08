/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.controller;

import br.com.software.model.Vendas;
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.VendasDao;
import java.util.List;

/**
 *
 * @author Julio
 */
public class VendasController {
    
    VendasDao vendasDao = DaoFactory.createVendaDao();
    
   public void inserirVenda(Vendas venda){
       vendasDao.insert(venda);
   }
   
   public List retornaTodasVendas(){
       
       return vendasDao.findAll();
   
       }
   
   }

