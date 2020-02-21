boolean pesquisar ( String palavra )
{
	return
}

boolean pesquisarNo2 ( No2 i, String palavra )
{
	boolean resposta = false;
	if ( palavra.equals(i.palavra))
	{
		resposta = true;
	}
	else if ( palavra.compareTo(i.palavra) == -1 )
	{
		pesquisarNo2 ( i.esq, palavra );
	}
	else if ( palavra.compareTo(i.palavra) == 1 )
	{
		pesquisarNo2 ( i.dir, palavra );
	}
	return resposta;
}

void inserir ( String palavra )
{
	return inserir ( No1 i, palavra);
}

void inserir ( No1 i, palavra )
{
	if ( i.charAt(0) == palavra.charAt(0))
	{
		inserir ( i.outra, palavra );
	}
	else if ( palavra.charAt(0) < i.charAt(0) )
	{
		inserir ( i.esq, palavra );
	}
	else if ( palavra.charAt(0) > i.charAt(0) )
	{
		inserir ( i.dir, palavra );
	}
}

void inserir ( No2 i, palavra )
{
	if ( i == null)
	{
		i = new No2(palavra);
	}
	else if ( palavra.compareTo(i.palavra) == -1 )
	{
		inserir ( i.esq, palavra );
	}
	else
	{
		inserir ( i.dir, palavra );
	}
}

void remover ( String palavra )
{
	return ( remover ( No1 j, palavra ); 
}

void remover ( No1 j, palavra )
{
	if ( j.palavra.equals(palavra)
}
