USE BDSpotPer398067

GO

SELECT c.Cod_Compra, a.CD_Alb AS CD, a.Desc_Alb AS Descricao, c.Preco_Compra AS Preco
FROM dbo.Album a, dbo.Compra c
WHERE a.Cod_Alb = c.Alb_Compra AND c.Preco_Compra > (SELECT AVG(Preco_Compra) FROM dbo.Compra)
ORDER BY c.Preco_Compra DESC

GO