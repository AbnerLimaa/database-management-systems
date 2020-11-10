USE BDSpotPer398067

GO

INSERT INTO dbo.Periodo_Musical VALUES('Classico', CONVERT(date, '01/01/1750', 103), CONVERT(date, '01/01/1830', 103));
INSERT INTO dbo.Periodo_Musical VALUES('Barroco', CONVERT(date, '01/01/1500', 103), CONVERT(date, '01/01/1700', 103));
INSERT INTO dbo.Periodo_Musical VALUES('Medieval', CONVERT(date, '01/01/0500', 103), CONVERT(date, '01/01/1500', 103));
INSERT INTO dbo.Periodo_Musical VALUES('Renascenca', CONVERT(date, '01/01/1300', 103), CONVERT(date, '01/01/1600', 103));

GO