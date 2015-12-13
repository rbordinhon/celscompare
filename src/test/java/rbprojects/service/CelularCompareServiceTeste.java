package rbprojects.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rbprojects.config.CelsCompareConfig;
import rbprojects.config.PersistenceConfigs;
import rbprojects.dto.CelularInfoDTO;
import rbprojects.dto.ComparativoCelularDTO;
import rbprojects.dto.RequisitoCelularDTO;
import rbprojects.dto.RequisitoComparativoDTO;

public class CelularCompareServiceTeste {

	private static AnnotationConfigApplicationContext ctx;
	private static CelsCompareService service;
	private static CelsCompareServiceHelper helper;
	
	@BeforeClass
	public static void  beforeClass(){
		if(ctx == null){
			System.setProperty("ambiente","teste");
			ctx = new AnnotationConfigApplicationContext();
			ctx.register(CelsCompareConfig.class);
			ctx.register(PersistenceConfigs.class);
			ctx.refresh();
			service = ctx.getBean(CelsCompareService.class);
			helper = ctx.getBean(CelsCompareServiceHelper.class); 
		}
	}
	
	
	/*
	 * Teste para buscar 10 elementos estabelecidos e validar os dados de um dos elementos
	 */
	@Test
	public void testeAddFavorito(){
		voteFavorito(8, 20);
	    voteFavorito(9, 15);
		voteFavorito(7, 12);
	    voteFavorito(10, 12);
	    voteFavorito(6, 11);
	    voteFavorito(4, 10);
		voteFavorito(2, 6);
		voteFavorito(5, 5);
	    voteFavorito(3, 1);
	    voteFavorito(1, 2);
		CelularInfoDTO vo = helper.createCelularInfo(6);
		Assert.assertEquals(11,vo.votacoes);    
		Assert.assertEquals("LG G4 Stylus HDTV 16 GB",vo.modelo);
		Assert.assertEquals(6,vo.idCelular);    
		   
	    
	    CelularInfoDTO[] vos = service.findAllInfosOrderByFavorito();
	    int i = 0;
	    Assert.assertEquals(20, vos[i++].votacoes);    
	    Assert.assertEquals(15, vos[i++].votacoes);    
	    Assert.assertEquals(12, vos[i++].votacoes);    
	    Assert.assertEquals(12, vos[i++].votacoes);    
	    Assert.assertEquals(11, vos[i++].votacoes);    
	    Assert.assertEquals(10, vos[i++].votacoes);    
	    Assert.assertEquals(6, vos[i++].votacoes);    
	    Assert.assertEquals(5, vos[i++].votacoes);    
	    Assert.assertEquals(2, vos[i++].votacoes);    
	    Assert.assertEquals(1, vos[i++].votacoes);    
	  
	     
	
	}
	private void voteFavorito( long id,long quant){
		for (int i = 0; i < quant; i++) {
			service.addFavorito(id);
		}
	}
	
	
	/*
	 * Teste para buscar 10 elementos estabelecidos e validar os dados de um dos elementos
	 */
	@Test
	public void testeBuscaCelulares(){
	     CelularInfoDTO[] infos  = service.findAllInfos();
	     Assert.assertTrue("Numero de elementos da busca invalido", infos.length== 10); 
	     final int item = 5;
	     CelularInfoDTO info = infos[item-1];
	     Assert.assertEquals(info.modelo, "Motorola Moto X Play Colors 32GB");
	     
	     RequisitoCelularDTO[] req = helper.findRequisitosByIdCelular(info.idCelular);
	     Assert.assertTrue("Numero de requisitos da busca invalido", req.length== 5);
	     int requisitosInformados = 0;
	     for (RequisitoCelularDTO requisitoCelularVO : req) {
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.CAMERA_FRONTAL_RESOLUCAO)){
		    	requisitosInformados++;
		    	Assert.assertEquals(requisitoCelularVO.valor,5d);
			}
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.CAMERA_TRASEIRA_RESOLUCAO)){
		       	requisitosInformados++;
				Assert.assertEquals(requisitoCelularVO.valor,21d);
			}
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.TAMANHO_DA_TELA)){
		       	requisitosInformados++;
				Assert.assertEquals(requisitoCelularVO.valor,5.5d);
			}
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.PRECO)){
		       	requisitosInformados++;
				Assert.assertEquals(requisitoCelularVO.valor,1301.52d);
			}
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.MEMORIA_INTERNA)){
		       	requisitosInformados++;
				Assert.assertEquals(requisitoCelularVO.valor,32.0d);
			}
		 }
	     Assert.assertTrue("Ha requisitos repetidos na busca", requisitosInformados== 5);
	     
	     
	
	}
	
	@Test
	public void testeCompareREquisitos(){
	     ComparativoCelularDTO comparativo = service.compareCels(5, 2);
		 Assert.assertEquals(comparativo.descricaoCelular1,"Motorola Moto X Play Colors 32GB");
		 Assert.assertEquals(comparativo.descricaoCelular2,"Samsung Galaxy S6 Edge");
		 /**
		  * insert into celular_info(ID_CELULAR_INFO,DS_MODELO,NR_MEMORIA,NR_CAMERA_TRASEIRA_MP,NR_TELA_TAMANHO,NR_PRECO) 
values(2,'Samsung Galaxy S6 Edge',32,27.7,5.1,2199.12 );
insert into celular_info(ID_CELULAR_INFO,DS_MODELO,NR_MEMORIA,NR_CAMERA_TRASEIRA_MP,NR_TELA_TAMANHO,NR_PRECO,NR_CAMERA_FRONTAL_MP)
values(5,'Motorola Moto X Play Colors 32GB',32,21,5.5, 1301.52,5);
		  */
		 
		 RequisitoComparativoDTO[] req = comparativo.requisitos;
	     Assert.assertTrue("Numero de requisitos da busca invalido", req.length== 5);
	     int requisitosInformados = 0;
	     for (RequisitoComparativoDTO requisitoCelularVO : req) {
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.CAMERA_FRONTAL_RESOLUCAO)){
		    	requisitosInformados++;
		       	Assert.assertEquals("5 Mp",requisitoCelularVO.valorCelular1);
		    	Assert.assertEquals("",requisitoCelularVO.valorCelular2);
		    	Assert.assertEquals(true, requisitoCelularVO.celular1emelhor);
		    	Assert.assertEquals(false, requisitoCelularVO.celular2emelhor);
		    	
		 	}
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.CAMERA_TRASEIRA_RESOLUCAO)){
		       	requisitosInformados++;
		    	Assert.assertEquals("21 Mp",requisitoCelularVO.valorCelular1);
		    	Assert.assertEquals("27,7 Mp",requisitoCelularVO.valorCelular2);
		      	Assert.assertEquals(false, requisitoCelularVO.celular1emelhor);
		    	Assert.assertEquals(true, requisitoCelularVO.celular2emelhor);
		    }
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.TAMANHO_DA_TELA)){
		       	requisitosInformados++;
		       	Assert.assertEquals("5.5",requisitoCelularVO.valorCelular1);
		    	Assert.assertEquals("5.1",requisitoCelularVO.valorCelular2);
		    	Assert.assertEquals(true, requisitoCelularVO.celular1emelhor);
		    	Assert.assertEquals(false, requisitoCelularVO.celular2emelhor);
		  
		    }
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.PRECO)){
		       	requisitosInformados++;
		       	Assert.assertEquals("R$ 1.301,52",requisitoCelularVO.valorCelular1);
		    	Assert.assertEquals("R$ 2.199,12",requisitoCelularVO.valorCelular2);
		    	Assert.assertEquals(true, requisitoCelularVO.celular1emelhor);
		    	Assert.assertEquals(false, requisitoCelularVO.celular2emelhor);
			}
		    if(requisitoCelularVO.descricao.equals(CelsCompareService.MEMORIA_INTERNA)){
		       	requisitosInformados++;
		    	Assert.assertEquals("32 GB",requisitoCelularVO.valorCelular1);
		    	Assert.assertEquals("32 GB",requisitoCelularVO.valorCelular2);
		    	Assert.assertEquals(false, requisitoCelularVO.celular1emelhor);
		    	Assert.assertEquals(false, requisitoCelularVO.celular2emelhor);
			}
		 }
	     Assert.assertTrue("Ha requisitos repetidos na busca", requisitosInformados== 5);
	
		 
	     
	     
	     
	     
	
	}
	
}
