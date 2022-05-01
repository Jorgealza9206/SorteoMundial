import javax.swing.*;
import java.util.*;

public class Main {
    static Random random = new Random();

    static int NumBalota(int num){
        return random.nextInt(num);
    }

    static String[][] SacarBalota(String[][] bombo, int numBalotas, int balotaRandom, int Bombo){
        String aux = bombo[Bombo][balotaRandom];
        for (int i = 0; i < (numBalotas-balotaRandom); i++){
            bombo[Bombo][balotaRandom+i] = bombo[Bombo][balotaRandom+i+1];
        }
        bombo[Bombo][numBalotas] = aux;
        return bombo;
    }

    static String[] SacarLetra(String[] bombo, int numBalotas, int balotaRandom){
        String aux = bombo[balotaRandom];
        for (int i = 0; i < (numBalotas-balotaRandom); i++){
            bombo[balotaRandom+i] = bombo[balotaRandom+i+1];
        }
        bombo[numBalotas] = aux;
        return bombo;
    }

    public static void main(String[] args) {

        int p = 1; //Incrementador de las posiciones del escalafon FIFA a la hora de introducir los paises a los bombos
        int balotaPais = 7; //Número de grupos -1
        int balotaGrupo = 3;//Número de posiciones -1
        int _balotaGrupo;//Número de balota producto del sorteo
        int _balotaPais;//Número auxiliar, producto del sorteo, por el cual se obtiene un país sorteado
        String[] letra = new String[]{"A","B","C","D","E","F","G","H"}; //Letras que ayudarán en el sorteo
        String[] LETRA = new String[]{"A","B","C","D","E","F","G","H"};
        String[][] bombos =
                new String[][]{{"Qatar","Brasil","Bélgica","Francia","Argentina","Inglaterra","España","Portugal"},
                        {"México","Paises Bajos","Dinamarca","Alemania","Uruguay","Suiza","Estados Unidos","Croacia"},
                        {"Senegal","Irán","Japón","Marruecos","Serbia","Polonia","Corea del Sur","Túnez"},
                        {"Camerún","Canadá","Ecuador","Arabia Saudita","Ghana","UEFA","CONCACAF-OFC","AFC-CONMEBOL"}};
        String[][] bombosGrupos = new String[8][3];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++){
                bombosGrupos[i][j] = String.valueOf(letra[i]) + String.valueOf(j+2);
            }
        }
        Map<String,List<String>> grupo = new HashMap<String,List<String>>();
        grupo.put("Group A",new ArrayList<String>());//Mapas en donde se planea almacenar los grupos
        grupo.put("Group B",new ArrayList<String>());//Clave: Grupo, Valor: Lista de paises
        grupo.put("Group C",new ArrayList<String>());
        grupo.put("Group D",new ArrayList<String>());
        grupo.put("Group E",new ArrayList<String>());
        grupo.put("Group F",new ArrayList<String>());
        grupo.put("Group G",new ArrayList<String>());
        grupo.put("Group H",new ArrayList<String>());
        /*bombos[0][0] = JOptionPane.showInputDialog("Ingrese al país organizador"); //Ingresamos al país organizador
        //System.out.println(bombos[0][0]);
        for (int i = 1; i < 8; i++) {
            bombos[0][i]=JOptionPane.showInputDialog("Ingrese el país en la posición " + p); //Ingresamos al resto
            //System.out.println("["+0+"]"+"["+i+"]"+bombos[0][i]);                          // del primer bombo
            p++;
        }
        for (int i = 1; i < 3; i++) { //Ingresamos los paises del resto de los bombos
            for (int j = 0; j < 8; j++) {
                bombos[i][j]=JOptionPane.showInputDialog("Ingrese el país en la posición " + p);
                //System.out.println("["+i+"]"+"["+j+"]"+bombos[i][j]);
                p++;
            }
        }*/
        //System.out.println(bombos);

        //Group_A.add(2,bombos.get(0).get(0));
        JOptionPane.showMessageDialog(null,bombos[0][0]);
        balotaPais--;
        bombos = SacarBalota(bombos,7,0,0);
        JOptionPane.showMessageDialog(null,letra[0]+"1");
        //System.out.println(Group_A);

        // Se muestra el país organizador y su posicion

        for(balotaGrupo = 3; balotaGrupo >= 0; balotaGrupo--){ // Se van mostrando el resto de los paises
            for(balotaPais = (balotaGrupo == 3)?6:7; balotaPais >= 0; balotaPais--){ // Pone 6 o 7 dependiendo si es el primer bombo, ya que el pais organizador ya esta sorteado
                _balotaPais = NumBalota(balotaPais+1); //Se saca el numero aleatorio para mostrar el país y sacar la balota del juego
                JOptionPane.showMessageDialog(null,bombos[3-balotaGrupo][_balotaPais]);// Se muestra el país
                bombos = SacarBalota(bombos,7,_balotaPais,3-balotaGrupo); // Saca al país del bombo
                if(balotaGrupo == 3){
                    JOptionPane.showMessageDialog(null,letra[7-balotaPais]+1);
                }
                else{
                    String balGrupoDes = JOptionPane.showInputDialog("Grupo",letra[0]); //Confirma o pregunta el grupo según el país, puesto que no deben ser de la misma conf.
                    int grupos = Arrays.asList(LETRA).indexOf(balGrupoDes);
                    letra = SacarLetra(letra,7,(grupos-(7-balotaPais)<=0)?0:grupos-(7-balotaPais));//Saca la letra ya jugada
                    _balotaGrupo = NumBalota(balotaGrupo+1); //Se sortea el numero de la balota del grupo
                    JOptionPane.showMessageDialog(null,bombosGrupos[grupos][_balotaGrupo]);// Se muestra la posición dentro del grupo
                    bombosGrupos = SacarBalota(bombosGrupos,2,_balotaGrupo,grupos);// Se saca la balota del juego
                }
            }
        }
    }

}
