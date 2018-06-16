package ibm.code.challenge.views.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import ibm.code.challenge.R;

public class SearchSuggestionsAdapter extends RecyclerView.Adapter<SearchSuggestionsAdapter.SearchSuggestionsVH>{

    ArrayList<String> strings;
    OnSuggestionClickListener onSuggestionClickListener;

    public SearchSuggestionsAdapter(OnSuggestionClickListener onSuggestionClickListener, ArrayList<String> strings){
        this.onSuggestionClickListener = onSuggestionClickListener;
        this.strings = strings;
    }


    @Override
    public SearchSuggestionsVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_suggestion, parent, false);
        return new SearchSuggestionsVH(view);
    }

    @Override
    public void onBindViewHolder(SearchSuggestionsVH holder, int position) {

        if(holder instanceof SearchSuggestionsVH && strings!=null)
            holder.bindData(strings.get(position));

    }

    @Override
    public int getItemCount() {
        return strings!=null? strings.size():0;
    }

    public class SearchSuggestionsVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout parent;
        TextView tv_query;

        public SearchSuggestionsVH(View itemView) {
            super(itemView);
            parent =itemView.findViewById(R.id.parent);
            tv_query = itemView.findViewById(R.id.tv_query);
            parent.setOnClickListener(this);
        }

        public void bindData(String string){

            if(StringUtils.isNotEmpty(string)) {
                parent.setTag(string);
                tv_query.setText(string);
            }

        }

        @Override
        public void onClick(View view) {

            if(onSuggestionClickListener!=null && view.getTag() instanceof String)
                onSuggestionClickListener.onSuggestionClick((String)view.getTag());
        }
    }

    public interface OnSuggestionClickListener{
        void onSuggestionClick(String text);
    }
}
