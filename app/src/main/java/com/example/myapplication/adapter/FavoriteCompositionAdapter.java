package com.example.myapplication.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.CompositionInfo;

import java.util.List;


public class FavoriteCompositionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;


    private ILoadMore loadMore;
    // загрузка
    private boolean isLoading;
    private Activity activity;



    // Список композиций
    private List<CompositionInfo> items;

    private int visibleThreshold = 20;

    private int lastVisibleItemPosition, totalItemCount;

    public FavoriteCompositionAdapter(RecyclerView recyclerView, Activity activity, List<CompositionInfo> items) {
        this.activity = activity;
        this.items = items;

        // Чето делает со списокм
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

        // Слушатель прокрутки, реагирует на любое изменение позиции
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // получаем количество элементов в списке
                totalItemCount = linearLayoutManager != null ? linearLayoutManager.getItemCount() : 0;
                // позиция в списке последнего  видимого элемента в списке
                lastVisibleItemPosition = linearLayoutManager != null ? linearLayoutManager.findLastVisibleItemPosition() : 0;
                System.out.println("lastVisible: " + lastVisibleItemPosition);
                // если нет загрузки и количество элементов меньше позиции последнего видимого элемента + 5 (what?)
                // @todo +visibleThreshold под вопросом
                if (!isLoading && (totalItemCount <= lastVisibleItemPosition + visibleThreshold)) {

                    if (loadMore != null) {
                        loadMore.onLoadMore();
                    }
                    System.out.println("LOADING");
                    isLoading = true;
                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.item_layout, parent,false);
            return new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {
            CompositionInfo compositionInfo = items.get(position);
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.compositionName.setText(compositionInfo.getCompositionName());
            viewHolder.authorName.setText(compositionInfo.getAuthorName());

            // Добавить в заказ, Добавить в избранное
            viewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(viewHolder.btnMore.getContext(),
                            viewHolder.btnMore);
                    popupMenu.getMenuInflater().inflate(R.menu.composition_menu, popupMenu.getMenu());


                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Добавить в заказ
                            if (item.getItemId() == 0) {

                                // Добавить в избранное
                            } else if (item.getItemId() == 1) {

                            }
                            // Добавить в заказ, Добавить в избранное
                            Toast.makeText(viewHolder.btnMore.getContext(), "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded() {
        System.out.println("STOP LOAD");
        isLoading = false;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.favoriteCompositionProgressBar);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView compositionName, authorName;
        ImageButton btnMore;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            btnMore = itemView.findViewById(R.id.moreBtn);
            compositionName = itemView.findViewById(R.id.compositionName);
            authorName = itemView.findViewById(R.id.authorName);
        }
    }


    public interface OnFavoriteCompositionListener {
        void onFavoriteCompositionClick(int position);
    }
}
