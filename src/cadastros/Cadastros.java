package cadastros;

import model.Aluno;
import model.Pessoa;
import repository.Repository;
import util.Formatadores;

/**
 * Classe responsável pela inserção dos dados de cadastro
 * de Pessoas e Alunos na memória.
 *
 * @author Victor Garcia Gallinari
 * @since 23/03/2023
 */
public class Cadastros {
    Repository<Pessoa> repository;

    /**
     * Método responsável por salvar cadastros na memória, de pessoas e alunos.
     * @param repository{@code Repository<T>}
     *      - Salvamento de cadastros.
     */
    public Cadastros (Repository<Pessoa> repository){

        repository.salvar(new Pessoa("Tuliano Fedo", "11435675456", Formatadores.stringParaData("12/11/1887")));
        repository.salvar(new Pessoa("Dada Maravilha", "11435675111", Formatadores.stringParaData("12/11/1881")));
        repository.salvar(new Pessoa("Axolote Noda", "11435675222", Formatadores.stringParaData("12/11/1882")));
        repository.salvar(new Pessoa("Topete Palo", "11435675333", Formatadores.stringParaData("12/11/1883")));
        repository.salvar(new Pessoa("Binel Fronte", "11435675444", Formatadores.stringParaData("12/11/1884")));
        repository.salvar(new Pessoa("Zeca Salgadinho", "11435675555", Formatadores.stringParaData("12/11/1885")));
        repository.salvar(new Pessoa("Chico Duarte", "11435675666", Formatadores.stringParaData("12/11/1886")));
        repository.salvar(new Pessoa("Amira Santiago", "11435672323", Formatadores.stringParaData("12/11/1889")));
        repository.salvar(new Pessoa("Tonico Nico", "11435675661", Formatadores.stringParaData("12/11/1881")));
        repository.salvar(new Pessoa("Fontana Abigail", "11435675660", Formatadores.stringParaData("12/11/1882")));

        repository.salvar(new Aluno("Estudantio", "11435675011", Formatadores.stringParaData("12/11/1899"), 6.0));
        repository.salvar(new Aluno("Alunio Aum4", "11435675012", Formatadores.stringParaData("12/11/1900"), 6.7));
        repository.salvar(new Aluno("Istoegio Alungi", "11435675013", Formatadores.stringParaData("12/11/1901"), 7.0));
        repository.salvar(new Aluno("Heleno Inteligente", "11435675014", Formatadores.stringParaData("12/11/1902"), 8.2));
        repository.salvar(new Aluno("Tambem AlunoHoje", "11435675015", Formatadores.stringParaData("12/11/1903"), 2.4));
        repository.salvar(new Aluno("Brito professor e aluno", "11435675016", Formatadores.stringParaData("12/11/1904"), 5.5));
        repository.salvar(new Aluno("Geografico Man", "11435675017", Formatadores.stringParaData("12/11/1905"), 6.1));
        repository.salvar(new Aluno("Martin Luther King", "11435675013", Formatadores.stringParaData("12/11/1928"), 9.9));
        repository.salvar(new Aluno("Cassiano Cabral", "11435675011", Formatadores.stringParaData("12/11/1990"), 2.4));
        repository.salvar(new Aluno("Visoneiro Caipira", "11435675099", Formatadores.stringParaData("12/11/1999"), 8.0));
    }
}
