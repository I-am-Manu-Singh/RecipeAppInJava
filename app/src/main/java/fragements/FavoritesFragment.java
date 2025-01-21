package fragements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neatroots.FBLoginAuthApp.R;
import com.neatroots.FBLoginAuthApp.Recipe;

import java.util.ArrayList;
import java.util.List;

import adapter.RecipeAdapter;

public class FavoritesFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourite, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize the data and adapter
        List<Recipe> recipes = getDummyData();
        RecipeAdapter adapter = new RecipeAdapter(recipes);
        recyclerView.setAdapter(adapter);
//        return view;

        return rootView;

    }



    private List<Recipe> getDummyData() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Favourite1", "Delicious cheesy pizza", R.drawable.ri_pizza));
        recipes.add(new Recipe("Favourite2", "Creamy Alfredo pasta", R.drawable.ri_pasta));
        recipes.add(new Recipe("Favourite3", "Juicy beef burger", R.drawable.ri_burger));
        recipes.add(new Recipe("Favourite4", "Fresh sushi with wasabi", R.drawable.ri_sushi));
        recipes.add(new Recipe("Favourite5", "Spicy Mexican tacos", R.drawable.ri_tacos));
        recipes.add(new Recipe("Favourite6", "Delicious cheesy pizza", R.drawable.ri_pizza));
        recipes.add(new Recipe("Favourite7", "Creamy Alfredo pasta", R.drawable.ri_pasta));
        recipes.add(new Recipe("Favourite8", "Juicy beef burger", R.drawable.ri_burger));
        recipes.add(new Recipe("Favourite9", "Fresh sushi with wasabi", R.drawable.ri_sushi));
        return recipes;
    }
}
