import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario() {
        super();
        this.salario = BigDecimal.ZERO;
        this.funcao = "";

    }

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salário inválido.");
        }
        if (funcao == null || funcao.isEmpty()) {
            throw new IllegalArgumentException("Função inválida.");
        }
        this.salario = salario;
        this.funcao = funcao;

    }

    public String getFuncao() {
        return funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public boolean aumentarSalario(BigDecimal percentual) {
        if (percentual.compareTo(BigDecimal.ZERO) > 0) {
            percentual = percentual.divide(BigDecimal.valueOf(100));
            BigDecimal novoSalario = percentual.multiply(getSalario()).add(getSalario());
            novoSalario = novoSalario.setScale(2, RoundingMode.HALF_UP);
            setSalario(novoSalario);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato de data
        DecimalFormatSymbols formatoSimbolosSalario = new DecimalFormatSymbols(); // Símbolos para formatação de salário
        formatoSimbolosSalario.setDecimalSeparator(',');
        formatoSimbolosSalario.setGroupingSeparator('.');
        DecimalFormat formatoSalario = new DecimalFormat("###,###.##", formatoSimbolosSalario); // Formato de salário

        return String.format("Nome: %s; Data de Nascimento: %s; Idade: %d; Salário: %s; Função: %s\n", getNome(),
                getDataNascimento().format(formatoData), getIdade(), formatoSalario.format(getSalario()), getFuncao());

    }

}
