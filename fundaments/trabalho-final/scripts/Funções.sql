USE BDSpotPer398067

GO

CREATE FUNCTION dbo.AlbunsCompositor(@NOME varchar(255))
RETURNS TABLE
AS
RETURN
(
	SELECT DISTINCT a.Desc_Alb
	FROM dbo.Album a, dbo.Faixa f, dbo.Criada_Faixa_Comptor cfc, dbo.Compositor c
	WHERE a.Cod_Alb = f.Alb_Faixa AND
	      f.Cod_Faixa = cfc.Cod_Faixa_Criada AND
		  c.Cod_Comptor = cfc.Cod_Comptor_Criada AND
		  c.Nome_Comptor = @NOME
);

GO