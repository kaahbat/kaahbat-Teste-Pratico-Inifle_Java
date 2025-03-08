import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa() {
        this.nome = "";
        this.dataNascimento = LocalDate.now();

    }

    public Pessoa(String nome, LocalDate dataNascimento) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }
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
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        // classe Period é usada para representar um período de tempo entre duas datas
        // pois é preciso para calcular a idade considerer a data atual
        Period periodoNascimento = Period.between(dataNascimento, LocalDate.now());
        return periodoNascimento.getYears();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato de data
        return String.format("Nome: %s; Data de Nascimento: %s;", getNome(), getDataNascimento().format(formatoData));
    }
}
