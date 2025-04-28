CREATE TABLE usuario (
    idusuario INT NOT NULL AUTO_INCREMENT,
    usuario VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    PRIMARY KEY (idusuario)
);

CREATE TABLE historico (
	idhistorico INT NOT NULL AUTO_INCREMENT,
	idusuario INT NOT NULL,
	senha VARCHAR(50) NOT NULL, 
	PRIMARY KEY(idhistorico),
	FOREIGN KEY(idusuario) references usuario(idusuario)
);

INSERT INTO usuario(usuario, senha) values ("Leandro Luis Coutinho da Silva", "26122001");
INSERT INTO usuario(usuario, senha) values ("Luciano Pereira", "17122006");

select * from usuario;
select * from historico;