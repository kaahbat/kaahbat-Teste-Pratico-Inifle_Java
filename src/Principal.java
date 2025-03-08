import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

        public static void main(String[] args) {
                List<Funcionario> funcionarios = new ArrayList<>();

                // Adiciona funcionários à lista
                funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"),
                                "Operador"));
                funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"),
                                "Operador"));
                funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"),
                                "Coordenador"));
                funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"),
                                "Diretor"));
                funcionarios
                                .add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"),
                                                "Recepcionista"));
                funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),
                                "Operador"));
                funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"),
                                "Contador"));
                funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"),
                                "Gerente"));
                funcionarios
                                .add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"),
                                                "Eletricista"));
                funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"),
                                "Gerente"));

                // Remove funcionário com nome "João"
                funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

                // Imprime a lista de funcionários
                funcionarios.forEach(System.out::println);

                aumentarSalariosFuncionarios(funcionarios, new BigDecimal("10.0"));

                // agrupando funcionarios por funcao
                Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                                .collect(Collectors.groupingBy(Funcionario::getFuncao));

                // imprimindo funcionarios por funcao
                imprimeFuncionarios(funcionariosPorFuncao);

                // lista de meses de aniversario
                List<Integer> listaAniversarios = List.of(10, 12);
                // imprime aniversariantes do mes de outubro e dezembros
                imprimeAniversariantes(funcionarios, listaAniversarios);

                // imprime o funcionario mais velho e exibe só nome e idade antes verificando se
                imprimeFuncionarioMaisVelho(funcionarios);

                // imprime a lista de funcionarios ordenada por ordem alfabética
                System.out.println("\nFuncionários em ordem alfabética:");
                funcionarios.stream()
                                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
                                .forEach(System.out::println);

                // imprime o total de salarios dos funcionarios
                BigDecimal totalSalarios = funcionarios.stream()
                                .map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);

                // Arredonda o total de salários para 2 casas decimais
                totalSalarios = totalSalarios.setScale(2, RoundingMode.HALF_UP);

                // Imprime o total de salários
                System.out.println("Total de salários: " + totalSalarios);

                imprimeQuantosSalariosGanhaCadaFuncionario(funcionarios, new BigDecimal("1212.00"));
        }

        public static void aumentarSalariosFuncionarios(List<Funcionario> funcionarios, BigDecimal percentual) {
                funcionarios.forEach(funcionario -> {
                        try {
                                if (!funcionario.aumentarSalario(new BigDecimal("10"))) {
                                        System.out.println("Erro ao aumentar o salário do funcionário "
                                                        + funcionario.getNome()
                                                        + ": Percentual inválido.");
                                }

                        } catch (Exception e) {
                                System.out.println("Erro inesperado ao aumentar o salário do funcionário "
                                                + funcionario.getNome()
                                                + ": " + e.getMessage());
                        }
                });
        }

        public static void imprimeFuncionarios(Map<String, List<Funcionario>> funcionariosPorFuncao) {
                funcionariosPorFuncao.forEach((funcao, lista) -> {
                        System.out.println("Funcionários da função " + funcao + ":");
                        lista.forEach(System.out::println);
                });
        }

        public static void imprimeAniversariantes(List<Funcionario> funcionarios, List<Integer> listaAniversarios) {
                if (funcionarios == null || listaAniversarios == null) {
                        System.out.println("Erro: Lista de funcionários ou lista de aniversários não pode ser nula.");
                        return;
                }

                // Imprime os funcionários que fazem aniversário nos meses da lista
                // Filtra os funcionários que fazem aniversário nos meses da lista
                List<Funcionario> aniversariantes = funcionarios.stream()
                                .filter(funcionario -> listaAniversarios
                                                .contains(funcionario.getDataNascimento().getMonthValue()))
                                .collect(Collectors.toList());

                if (!aniversariantes.isEmpty()) {
                        System.out.println("\nAniversariantes do mês:");
                        aniversariantes.forEach(System.out::println);
                } else {
                        System.out.println("Nenhum aniversariante encontrado para os meses especificados.");
                }
        }

        public static void imprimeFuncionarioMaisVelho(List<Funcionario> funcionarios) {
                // Verifica se a lista de funcionários é nula ou vazia
                if (funcionarios == null || funcionarios.isEmpty()) {
                        System.out.println("Erro: Lista de funcionários não pode ser nula ou vazia.");
                        return;
                }
                Funcionario funcionarioMaisVelho = Collections.max(funcionarios,
                                Comparator.comparing(Funcionario::getIdade));

                System.out.println("\nFuncionário mais velho: " + funcionarioMaisVelho.getNome() + ", Idade: "
                                + funcionarioMaisVelho.getIdade() + " anos");
        }

        public static void imprimeQuantosSalariosGanhaCadaFuncionario(List<Funcionario> funcionarios,
                        BigDecimal salarioMinimo) {
                // Verifica se a lista de funcionários é nula ou vazia
                if (funcionarios == null || funcionarios.isEmpty()) {
                        System.out.println("Erro: Lista de funcionários não pode ser nula ou vazia.");
                        return;
                }
                if (salarioMinimo == null || salarioMinimo.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("Erro: Salário mínimo inválido.");
                        return;
                }
                // imprime quantos salarios minimos ganha cada funcionario , salario 1212.00
                funcionarios.forEach(funcionario -> {
                        System.out.println(funcionario.getNome() + " ganha "
                                        + funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP)
                                                        .setScale(2, RoundingMode.HALF_UP)
                                        + " salários mínimos.");
                });
        }

}