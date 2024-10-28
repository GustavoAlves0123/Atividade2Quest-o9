class Conta {
    protected double saldo;
    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }
    public void sacar(double valor) {
        System.out.println("Método de saque genérico.");
    }
    public void depositar(double valor) {
        System.out.println("Método de depósito genérico.");
    }
    public void transferir(Conta destino, double valor) {
        System.out.println("Método de transferência genérico.");
    }
    public double getSaldo() {
        return saldo;
    }
}
class ContaCorrente extends Conta {
    public ContaCorrente(double saldoInicial) {
        super(saldoInicial);
    }
    @Override
    public void sacar(double valor) {
        double taxa = 0.01 * valor;
        if (super.getSaldo() >= valor + taxa) {
            super.saldo -= (valor + taxa);
            System.out.println("Saque de R$" + valor + " com taxa de R$" + taxa + " realizado.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }
    @Override
    public void transferir(Conta destino, double valor) {
        if (destino instanceof ContaPoupanca && super.getSaldo() >= valor) {
            super.saldo -= valor;
            destino.saldo += valor;
            System.out.println("Transferência de R$" + valor + " para conta poupança realizada.");
        } else {
            System.out.println("Transferência não permitida ou saldo insuficiente.");
        }
    }
}
class ContaPoupanca extends Conta {
    public ContaPoupanca(double saldoInicial) {
        super(saldoInicial);
    }
    @Override
    public void sacar(double valor) {
        double taxa = 0.05 * valor;
        if (super.getSaldo() >= valor + taxa) {
            super.saldo -= (valor + taxa);
            System.out.println("Saque de R$" + valor + " com taxa de R$" + taxa + " realizado.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }
    @Override
    public void depositar(double valor) {
        super.saldo += valor + (0.1 * valor);
        System.out.println("Depósito de R$" + valor + " com bônus de 10% realizado. Saldo atual: R$" + super.getSaldo());
    }
    @Override
    public void transferir(Conta destino, double valor) {
        if (destino instanceof ContaCorrente && super.getSaldo() >= valor) {
            super.saldo -= valor;
            destino.saldo += valor;
            System.out.println("Transferência de R$" + valor + " para conta corrente realizada.");
        } else {
            System.out.println("Transferência não permitida ou saldo insuficiente.");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        ContaCorrente contaCorrente = new ContaCorrente(1000);
        ContaPoupanca contaPoupanca = new ContaPoupanca(2000);
        contaCorrente.sacar(100);
        contaPoupanca.depositar(300);
        contaPoupanca.transferir(contaCorrente, 500);
        contaCorrente.transferir(contaPoupanca, 200);
    }
}
