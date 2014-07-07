package br.com.teste.teste_francisco;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity {

    AdapterMenu adapter;
    ArrayList<ItemListView> itens;
    ListView lv_opcoes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        lv_opcoes = (ListView) findViewById(R.id.lv_opcoes);

        itens = new ArrayList<ItemListView>();

        itens.add(new ItemListView("Titulo1", "Descrição do item 01", Color.RED));
        itens.add(new ItemListView("Titulo2", "Descrição do item 02", Color.BLUE));
        itens.add(new ItemListView("Titulo3", "Descrição do item 03", Color.YELLOW));
        itens.add(new ItemListView("Titulo4", "Descrição do item 04", Color.DKGRAY));




        adapter = new AdapterMenu(this, itens);
        adapter.findBy(R.layout.c_list_model, R.id.layoutLinear, R.id.tv_text, R.id.tv_descricao, R.id.lv_opcoes);
        lv_opcoes.setAdapter(adapter);



        lv_opcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), itens.get(position).getText() + " _ Color :" + itens.get(position).getcolor(), Toast.LENGTH_LONG).show();

                LinearLayout ln_cor = (LinearLayout) findViewById(R.id.cor);
                ln_cor.setBackgroundColor(itens.get(position).getcolor());
            }
        });

    }




    public class AdapterMenu extends BaseAdapter {

        private ArrayList<ItemListView> itens;
        private LayoutInflater inflater;

        private Context context;

        private int custom_list;
        private int background;
        private int text;
        private int descricao;
        private int layout;

        String cat = "Teste";

        private boolean cor = false, bBarra = false;

        private int corTexto, corFundo, corTexto1, corTexto2,corFundo1,corFundo2,opcao;


        /**
         * Cria um novo ArrayAdapter.
         * <br>Deve ser passado o context e a lista de que deve ser adicionada a View(ListView ou Spinner);
         *
         * <p>Exemplo:
         * <br>{@code AdapterMenu adapterMenu = new AdapterMenu(this, itens);}
         */
        public AdapterMenu(Context context, ArrayList<ItemListView> list){
            this.itens = list;
            this.context = context;
            inflater = LayoutInflater.from(context);

            opcao = 1;
        }



        /**
         * Deve ser passado os identificadores dos componentes que serão utilizados na lista.
         * <br>obs.: este adapter só é compativel com layout's que contenham LinearLayout, segue exemplo em res/layout/custom_list.
         * <br> * caso não utilize todos os componentes colocar valor 0 e não adicionar nenhum valor em ItemListView, pois ira causar uma ANR.
         *
         * <p>Exemplo:
         * <br>{@code adapterMenu.findBy(R.layout.custom_list, R.id.imageView, R.id.titulo, R.id.descricao, R.id.layoutLinear);}
         */
        public void findBy(int custom_list, int background, int text, int descricao, int layout){
            this.custom_list = custom_list;
            this.background = background;
            this.text = text;
            this.descricao = descricao;
            this.layout = layout;
        }



        /**
         * Retorna o tamanho da lista.
         *<br>
         *@return int size;
         */
        @Override
        public int getCount() {
            return itens.size();
        }


        @Override
        public Object getItem(int position) {
            return itens.get(position);
        }

        /**
         * Retorna o identificador da posição.<br>
         * @param int position<br>
         * @return long id
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHoler viewHoler;

            if(view == null){
                view = inflater.inflate(custom_list,null);
                viewHoler = new ViewHoler();
                viewHoler.background = ((LinearLayout) view.findViewById(background));
                viewHoler.textView = ((TextView) view.findViewById(text));
                viewHoler.descricao = ((TextView) view.findViewById(descricao));

                view.setTag(viewHoler);
            }
            else{
                viewHoler = (ViewHoler) view.getTag();
            }

            ItemListView item = itens.get(position);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(layout);


            viewHoler.textView.setTextColor(item.getcolor());
            viewHoler.textView.setText(item.getText());

            view.setBackgroundColor(corFundo);
            viewHoler.background.setBackgroundColor(item.getcolor());

            viewHoler.descricao.setTextColor(Color.BLACK);
            viewHoler.descricao.setText(item.getDescricao());

            return view;
        }



        private int getCor(){
            int red = (int) (Math.random() * 128 + 127);
            int green = (int) (Math.random() * 128 + 127);
            int blue = (int) (Math.random() * 128 + 127);

            int color = 0xff << 24 | (red << 16) | (green << 8) | blue;
            return color;
        }

    }

    static class ViewHoler{
        TextView textView;
        TextView descricao;
        LinearLayout background;
    }

    public class ItemListView{

        private String string, descricao;
        private int icon;

        private int opcao;
        private int color = Color.WHITE;
        private int Tcolor = Color.WHITE;
        private int Dcolor = Color.WHITE;

        private boolean barra;

        /**
         * <p>
         * Cria um novo item apenas com um texto com uma descrição do item e barra
         * de sepração colorida.
         * </p>
         * <br>
         *
         * Exemplo: <br>
         * {@code ItemListView item = new ItemListView("Item1", "Descrição item 1", true, Color.RED);}
         * <br>
         */
        public ItemListView(String texto, String descricao, int color) {
            this.string = texto;
            this.descricao = descricao;
            //this.barra = barra;
            this.color = color;

            opcao = 3;
        }


        /**
         *
         * */


        public String getText() {
            switch (opcao) {
                case 5:
                    string = null;
                    break;
            }
            return string;
        }

        public String getDescricao() {
            switch (opcao) {
                case 3:
                    break;
                case 4:
                    break;
                default:
                    descricao = null;
                    break;

            }
            return descricao;
        }

        public int getcolor(){
            return color;
        }

    }

}
