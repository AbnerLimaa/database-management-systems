USE BDSpotPer398067

GO

SELECT TOP(1) g.Cod_Grav, g.Nome_Grav, p.Nome_Play, f.Desc_Faixa, COUNT(p.Cod_Play) AS Quantidade
FROM dbo.Gravadora g, dbo.Album a, dbo.Playlist p, dbo.Agrupa_Faixa_Play afp, dbo.Faixa f, dbo.Criada_Faixa_Comptor cfc, dbo.Compositor c
WHERE g.Cod_Grav = a.Grav_Alb AND 
      p.Cod_Play = afp.Cod_Play_Agrupa AND
	  afp.Cod_Faixa_Agrupa = f.Cod_Faixa AND
	  f.Alb_Faixa = a.Cod_Alb AND
	  f.Cod_Faixa = cfc.Cod_Faixa_Criada AND
	  c.Cod_Comptor = cfc.Cod_Comptor_Criada AND
	  c.Nome_Comptor = 'Antonin Dvorak'
GROUP BY g.Cod_Grav, g.Nome_Grav, p.Nome_Play, f.Desc_Faixa
ORDER BY COUNT(p.Cod_Play) DESC