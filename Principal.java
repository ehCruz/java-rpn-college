import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private ArrayList<String> operadores = new ArrayList<>();
    private ArrayList<String> output = new ArrayList<>();
    private int elementos = 0;

    private Scanner sc = new Scanner(System.in);

    public boolean expressaoValida(){
        //TODO
        return true;
    }    
    
    public void separaPilhas(String exp) {
        this.operadores.clear();
        this.output.clear();
        String aux;
        this.operadores.add(" ");
        for (int i = 0; i < exp.length(); i++) {
            aux = Character.toString(exp.charAt(i));
            if (aux.matches("[\\+\\-\\*\\/\\(\\)]")) {
                this.verificarPilhaOperacao(aux);
            } else {
                this.output.add(aux);
            }
        }
        for (int i = this.operadores.size(); i >= 1; i--) {
            this.output.add(this.operadores.remove(i - 1));
        }
        this.calcExpressao();
    }

    private void verificarPilhaOperacao(String aux) {
        if (!this.operadores.isEmpty()) {
            if (aux.equals("+") || aux.equals("-")) {
                if (!this.operadores.contains("+") || !this.operadores.contains("-") || !this.operadores.contains("*")
                        || !this.operadores.contains("/") || !this.operadores.contains("^")) {
                    while ((this.operadores.get(this.operadores.size() - 1).equals("-")
                            || this.operadores.get(this.operadores.size() - 1).equals("+")
                            || this.operadores.get(this.operadores.size() - 1).equals("*")
                            || this.operadores.get(this.operadores.size() - 1).equals("/")
                            || this.operadores.get(this.operadores.size() - 1).equals("^"))
                            && !this.operadores.get(this.operadores.size() - 1).equals("(")) {
                        this.output.add(this.operadores.remove(this.operadores.size() - 1));
                    }
                }
                this.operadores.add(aux);
            } else if (aux.equals("*") || aux.equals("/")) {
                if (!this.operadores.contains("*") || !this.operadores.contains("/")
                        || !this.operadores.contains("^")) {
                    while ((this.operadores.get(this.operadores.size() - 1).equals("*")
                            || this.operadores.get(this.operadores.size() - 1).equals("/")
                            || this.operadores.get(this.operadores.size() - 1).equals("^"))
                            && !this.operadores.get(this.operadores.size() - 1).equals("(")) {
                        this.output.add(this.operadores.remove(this.operadores.size() - 1));
                    }
                }
                this.operadores.add(aux);
            } else if (aux.equals("^")) {
                if (!this.operadores.contains("^")) {
                    while ((this.operadores.get(this.operadores.size() - 1).equals("^"))
                            && !this.operadores.get(this.operadores.size() - 1).equals("(")) {
                        this.output.add(this.operadores.remove(this.operadores.size() - 1));
                    }
                }
                this.operadores.add(aux);
            } else if (aux.equals(")")) {
                while (!this.operadores.get(this.operadores.size() - 1).equals("(")) {
                    this.output.add(this.operadores.remove(this.operadores.size() - 1));
                }
                this.operadores.remove(this.operadores.size() - 1);
            } else {
                this.operadores.add(aux);
            }
        }
    }

    private void calcExpressao() {
        ArrayList<String> sumAux = new ArrayList<>();
        // sumAux.add(" ");
        Integer soma = 0;
        int op1, op2;
        for (String elemento : output) {
            switch (elemento) {
            case "+":
                soma = Integer.parseInt(sumAux.remove(sumAux.size() - 1))
                        + Integer.parseInt(sumAux.remove(sumAux.size() - 1));
                sumAux.add(Integer.toString(soma));
                break;
            case "-":
                op2 = Integer.parseInt(sumAux.remove(sumAux.size() - 1));
                op1 = Integer.parseInt(sumAux.remove(sumAux.size() - 1));
                soma = op1 - op2;
                sumAux.add(Integer.toString(soma));
                break;
            case "*":
                soma = Integer.parseInt(sumAux.remove(sumAux.size() - 1))
                        * Integer.parseInt(sumAux.remove(sumAux.size() - 1));
                sumAux.add(Integer.toString(soma));
                break;
            case "/":
                op2 = Integer.parseInt(sumAux.remove(sumAux.size() - 1));
                op1 = Integer.parseInt(sumAux.remove(sumAux.size() - 1));
                soma = op1 / op2;
                sumAux.add(Integer.toString(soma));
                break;
            case "^":
                soma = Integer.parseInt(sumAux.remove(sumAux.size() - 1))
                        ^ Integer.parseInt(sumAux.remove(sumAux.size() - 1));
                break;
            default:
                sumAux.add(elemento);
                break;
            }
        }
        System.out.print("Postfix:");
        for (String el : output) {
            System.out.print(" " + el);
        }
        System.out.print("\nResultado: " + sumAux.get(0) + "\n");
    }

    private void processar() {
        int op;
        String exp;
        while (true) {
            System.out.print("\n\nInforme a expressao ou <0> para sair:");
            exp = sc.next();
            if (exp.equals("0")) {
                System.exit(0);
            } else {
                separaPilhas(exp);
            }
        }
    }

    public static void main(String[] args) {
        // new Principal().separaPilhas("(2+5*2)-(3+5)*7+(8/4)+1");
        new Principal().processar();
    }
}