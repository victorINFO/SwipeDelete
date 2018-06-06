package id.ndiappink.swipedelete;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;


public class SwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SwipeRecyclerViewAdapter.SimpleViewHolder> {

    private Context mContext;
    private ArrayList<Richiesta> richiestaList;

    public SwipeRecyclerViewAdapter(Context context, ArrayList<Richiesta> objects) {
        this.mContext = context;
        this.richiestaList = objects;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {

        if(position %2 == 1)
        {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }
        else
        {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));

        }
        final Richiesta item = richiestaList.get(position);

        viewHolder.tv_matricola.setText(item.getMatricola());
        viewHolder.tv_nomeEcognome.setText(item.getNomeEcognome());
        viewHolder.tv_giornoInizio.setText(item.getGiornoInizio());
        viewHolder.tv_oraInizio.setText(item.getOraInizio());
        viewHolder.tv_giornoFine.setText(item.getGiornoFine());
        viewHolder.tv_oraFine.setText(item.getOraFine());


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //dari kiri
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Bottom, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));

        //dari kanan
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));



        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });



  //      viewHolder.btnLocation.setOnClickListener(new View.OnClickListener() {
  //          @Override
  //          public void onClick(View v) {
   //             Toast.makeText(v.getContext(), "Clicked on Information " + viewHolder.tv_matricola.getText().toString(), Toast.LENGTH_SHORT).show();
   //         }
   //     });

        viewHolder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Clicked on Share " + viewHolder.tv_matricola.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    //    viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
     //       @Override
     //       public void onClick(View view) {
//
     //           Toast.makeText(view.getContext(), "Clicked on Edit  " + viewHolder.tv_matricola.getText().toString(), Toast.LENGTH_SHORT).show();
     //       }
    //    });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                richiestaList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, richiestaList.size());
                mItemManger.closeAllItems();
                Toast.makeText(v.getContext(), "Deleted " + viewHolder.tv_matricola.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return richiestaList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public TextView tv_matricola;
        public TextView tv_nomeEcognome;
        public TextView tv_giornoInizio;
        public TextView tv_oraInizio;
        public TextView tv_giornoFine;
        public TextView tv_oraFine;

        public ImageView Delete;
        public TextView Edit;
        public ImageView Share;
       // public ImageButton btnLocation;
        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            tv_matricola = (TextView) itemView.findViewById(R.id.tv_matricola);
            tv_nomeEcognome = (TextView) itemView.findViewById(R.id.tv_nomeCognome);
            tv_giornoInizio = (TextView) itemView.findViewById(R.id.tv_giornoInizio);
            tv_oraInizio = (TextView) itemView.findViewById(R.id.tv_oraInizio);
            tv_giornoFine = (TextView) itemView.findViewById(R.id.tv_giornoFine);
            tv_oraFine= (TextView) itemView.findViewById(R.id.tv_oraFine);

            Delete = (ImageView) itemView.findViewById(R.id.Delete);

            Share = (ImageView) itemView.findViewById(R.id.Share);
           // btnLocation = (ImageButton) itemView.findViewById(R.id.btnLocation);
        }
    }
}
