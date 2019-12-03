package br.edu.ifsp.agendasqlite.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.edu.ifsp.agendasqlite.R;
import br.edu.ifsp.agendasqlite.model.Contato;

public class ContatoAdapter
        extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>
        implements Filterable  {

    static List<Contato> contatos;
    List<Contato> contactListFiltered;

    private static ItemClickListener clickListener;


    public void adicionaContatoAdapter(Contato c)
    {
        contatos.add(0,c);

        Collections.sort(contatos, new Comparator<Contato>() {
            @Override
            public int compare(Contato o1, Contato o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });

        notifyDataSetChanged();

    }

    public void atualizaContatoAdapter(Contato c)
    {
        contatos.set(contatos.indexOf(c),c);
        notifyItemChanged(contatos.indexOf(c));
    }

    public void apagaContatoAdapter(Contato c)
    {
        int pos = contatos.indexOf(c);
        contatos.remove(pos);
        notifyItemRemoved(pos);


    }

    public List<Contato> getContactListFiltered()
    {
        return contactListFiltered;
    }

    public ContatoAdapter(List<Contato> contatos)
    {
        this.contatos = contatos;
        contactListFiltered=contatos;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.contato_celula,parent,false);

        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {
            if (contactListFiltered.get(position).isFavorito()) {
                holder.favorito.setImageDrawable(
                        holder.itemView.getContext().getDrawable(R.drawable.ic_star_yellow));
            } else {
                holder.favorito.setImageDrawable(
                        holder.itemView.getContext().getDrawable(R.drawable.ic_star_black));
            }

            holder.nome.setText(contactListFiltered.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contatos;
                } else {
                    List<Contato> filteredList = new ArrayList<>();
                    for (Contato row : contatos) {
                        if (row.getNome().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }
                    }
                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contactListFiltered = (ArrayList<Contato>) results.values;
                notifyDataSetChanged();

            }
        };
    }


    public class ContatoViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        final TextView nome;
        final ImageView favorito;

        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            favorito = itemView.findViewById(R.id.favoritar);

            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (ContatoAdapter.clickListener!=null)
                ContatoAdapter.clickListener.onItemClick(v, getAdapterPosition());
        }
    }


    public void setClickListener(ItemClickListener itemClickListener)
    {
        ContatoAdapter.clickListener = itemClickListener;

    }

    public  interface ItemClickListener
    {
        void onItemClick(View v, int position);
    }


}
