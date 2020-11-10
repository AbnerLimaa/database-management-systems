USE BDSpotPer398067

GO

CREATE TRIGGER TR_Limite_Faixas
ON dbo.Faixa
FOR INSERT, UPDATE AS
IF((SELECT COUNT(Alb_Faixa)
	FROM dbo.Faixa
	WHERE Alb_Faixa = (SELECT Alb_Faixa FROM INSERTED)) > 64)
BEGIN
	RAISERROR('Quantidade limite de faixas no album atingida',-1, -1)
	ROLLBACK TRANSACTION
END

GO

CREATE TRIGGER TR_Compra_Barroco
ON dbo.Compra
FOR INSERT, UPDATE AS
BEGIN
	DECLARE
		@ALBUM integer
		SELECT @ALBUM = Alb_Compra FROM INSERTED
	IF((SELECT COUNT(p.Cod_PerMus)
	    FROM dbo.Faixa f, dbo.Criada_Faixa_Comptor cfc, dbo.Compositor c, dbo.Periodo_Musical p
		WHERE f.Cod_Faixa = cfc.Cod_Faixa_Criada AND
		      c.Cod_Comptor = cfc.Cod_Comptor_Criada AND
			  p.Cod_PerMus = c.PerMus_Comptor AND
			  f.Alb_Faixa = @ALBUM AND
			  p.Desc_PerMus = 'Barroco') > 0)
	BEGIN
		IF((SELECT COUNT(Cod_Faixa)
		    FROM dbo.Faixa 
		    WHERE Alb_Faixa = @ALBUM AND
			      Tipo_Faixa = 'ADD') > 0)
		BEGIN
			RAISERROR('Nao e possivel adquirir o album', -1, -1)
			ROLLBACK TRANSACTION
		END
	END
END

GO

CREATE TRIGGER TR_Compra_Preco
ON dbo.Compra
FOR INSERT, UPDATE AS
BEGIN
	DECLARE 
		@PRECO integer,
		@ALBUM integer,
		@SOMA integer,
		@QUANT integer
		SELECT @PRECO = Preco_Compra FROM INSERTED
	SELECT @SOMA = 0, @QUANT = 0
	DECLARE Cursor_Album CURSOR SCROLL FOR
		SELECT Cod_Alb FROM dbo.Album
	OPEN Cursor_Album
	FETCH FIRST FROM Cursor_Album INTO @ALBUM
	WHILE(@@FETCH_STATUS = 0)
	BEGIN
		SELECT @SOMA = @SOMA + ISNULL(SUM(DISTINCT t.Preco_Compra), 0), @QUANT = @QUANT + ISNULL(COUNT(DISTINCT t.Cod_Compra), 0)
		FROM (SELECT DISTINCT f.Cod_Faixa, c.Preco_Compra, c.Cod_Compra
		      FROM dbo.Album a, dbo.Faixa f, dbo.Compra c
			  WHERE a.Cod_Alb = f.Alb_Faixa AND
					a.Cod_Alb = c.Alb_Compra AND
					a.Cod_Alb = @ALBUM AND
					'DDD' = ALL(SELECT f.Tipo_Faixa 
								FROM dbo.Faixa f 
								WHERE f.Alb_Faixa = @ALBUM)) t
		FETCH NEXT FROM Cursor_Album INTO @ALBUM 
	END
	IF(@QUANT <> 0 AND ((@SOMA / @QUANT)*3) < @PRECO)
	BEGIN
		RAISERROR('Nao e possivel adquirir o album', -1, -1)
		ROLLBACK TRANSACTION
	END
	DEALLOCATE Cursor_Album
END

GO