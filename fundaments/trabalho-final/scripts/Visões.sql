USE BDSpotPer398067

GO

CREATE VIEW VW_Albuns_Playlists(nome, quant) AS
	SELECT play.Nome_Play, COUNT(DISTINCT alb.Cod_Alb)
	FROM dbo.Playlist play, dbo.Agrupa_Faixa_Play agr, dbo.Faixa fai, dbo.Album alb
	WHERE play.Cod_Play = agr.Cod_Play_Agrupa AND fai.Cod_Faixa = agr.Cod_Faixa_Agrupa AND alb.Cod_Alb = fai.Alb_Faixa
	GROUP BY play.Nome_Play
GO