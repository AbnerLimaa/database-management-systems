CREATE DATABASE BDSpotPer398067 
ON
	PRIMARY
	(
		NAME='BDSpotPer',
		FILENAME='C:\Users\Abner Lima\Documents\FUBD\banco-de-dados\BDSpotPer.mdf',
		SIZE=5120KB,
		FILEGROWTH=1024KB
	),

	FILEGROUP BDSpotPer_fg1
	(
		NAME='BDSpotPer_11',
		FILENAME='C:\Users\Abner Lima\Documents\FUBD\banco-de-dados\BDSpotPer_11.ndf',
		SIZE=1024KB,
		FILEGROWTH=30%
	),
	(
		NAME='BDSpotPer_12',
		FILENAME='C:\Users\Abner Lima\Documents\FUBD\banco-de-dados\BDSpotPer_12.ndf',
		SIZE=1024KB,
		FILEGROWTH=30%
	),

	FILEGROUP BDSpotPer_fg2
	(
		NAME='BDSpotPer_21',
		FILENAME='C:\Users\Abner Lima\Documents\FUBD\banco-de-dados\BDSpotPer_21.ndf',
		SIZE=1024KB,
		FILEGROWTH=30%
	)

	LOG ON
	(
		NAME='BDSpotPer_log',
		FILENAME='C:\Users\Abner Lima\Documents\FUBD\banco-de-dados\BDSpotPer_log.ldf',
		SIZE=1024KB,
		FILEGROWTH=30%
	)