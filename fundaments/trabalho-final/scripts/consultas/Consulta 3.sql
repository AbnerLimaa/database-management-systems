USE BDSpotPer398067

GO

SELECT TOP(1) comptor.Nome_Comptor, COUNT(afp.Cod_Faixa_Agrupa) AS Quantidade
FROM dbo.Agrupa_Faixa_Play afp, dbo.Criada_Faixa_Comptor cfc, dbo.Compositor comptor 
WHERE afp.Cod_Faixa_Agrupa = cfc.Cod_Faixa_Criada AND cfc.Cod_Comptor_Criada = comptor.Cod_Comptor
GROUP BY comptor.Nome_Comptor
ORDER BY COUNT(cfc.Cod_Faixa_Criada) DESC

GO