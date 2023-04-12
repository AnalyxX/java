
package coleta.informacoes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

public class RedeColeta {
    public static void main(String[] args) {
        Looca looca = new Looca();
        
        Rede rede = looca.getRede();
        System.out.println(rede);
        
    }
}
