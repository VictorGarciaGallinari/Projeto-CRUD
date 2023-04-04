package model;

import java.util.Date;
import java.util.Locale;


/**
 * Classe responsável por definir os atributos de Alunos,
 * cadastrando nome, telefone, data de nascimento,
 * data de cadastro, data de alterações e notas,
 * que é a diferenciação de uma Aluno para uma pessoa.
 *
 * @author Victor Garcia Gallinari
 * @since 23/03/2023
 */
public class Aluno extends Pessoa{

    Date hoje = new Date();
    double notaFinal;

    public Aluno(String nome, String telefone, Date dataNascimento, double notaFinal) {
        super(nome, telefone, dataNascimento);
        this.notaFinal = notaFinal;
        this.dataCadastro = hoje;
        this.dataAlteracao = hoje;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return super.toString()
                + " - Nota final: " + String.format(Locale.US, "%.2f", this.notaFinal);
    }
}
