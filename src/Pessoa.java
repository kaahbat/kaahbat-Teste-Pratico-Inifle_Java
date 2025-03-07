import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;// LocalDate é uma classe do pacote java.time que representa uma data. Ela é
                                     // imutável e possui diversos métodos para manipulação de datas. formato:
                                     // yyyy-MM-dd

    public Pessoa() {

    }

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

}
