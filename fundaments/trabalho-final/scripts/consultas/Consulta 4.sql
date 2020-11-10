USE BDSpotPer398067

GO

SELECT p.Cod_Play, p.Nome_Play
FROM dbo.Playlist p, dbo.Agrupa_Faixa_Play afp, dbo.Faixa f, dbo.Criada_Faixa_Comptor cfc, dbo.Compositor c, dbo.Periodo_Musical per,
     dbo.Composicao compcao
WHERE p.Cod_Play = afp.Cod_Play_Agrupa AND
	  f.Cod_Faixa = afp.Cod_Faixa_Agrupa AND
	  f.Cod_Faixa = cfc.Cod_Faixa_Criada AND
	  f.Compcao_Faixa = compcao.Cod_Compcao AND
	  c.Cod_Comptor = cfc.Cod_Comptor_Criada AND
	  c.PerMus_Comptor = per.Cod_PerMus AND
	  per.Desc_PerMus = 'Barroco' AND
	  compcao.Desc_Compcao = 'Concerto'
GROUP BY p.Cod_Play, p.Nome_Play
HAVING COUNT(p.Cod_Play) = (SELECT COUNT(p2.Cod_Play) 
						    FROM dbo.Playlist p2, dbo.Agrupa_Faixa_Play afp 
							WHERE p2.Cod_Play = afp.Cod_Play_Agrupa AND p2.Cod_Play = p.Cod_Play)