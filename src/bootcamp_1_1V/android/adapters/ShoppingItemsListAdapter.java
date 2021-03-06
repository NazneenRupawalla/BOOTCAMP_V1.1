package bootcamp_1_1V.android.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidplugins.Callback;
import androidplugins.imagefetcher.ImageFetcher;
import bootcamp_1_1V.android.R;
import bootcamp_1_1V.android.models.Product;
import bootcamp_1_1V.android.repositories.ProductRepository;
import bootcamp_1_1V.android.services.ImageDownloader;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItemsListAdapter extends BaseAdapter {

    private final Context context;
    public List<Product> products = new ArrayList<Product>();

    public ShoppingItemsListAdapter(Context context, ArrayList<Product> products){
        this.context = context;
		this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.cell_layout,null);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView);
        TextView textView = (TextView) layout.findViewById(R.id.title);
        Product product = products.get(position);
        textView.setText(product.getTitle());
		new ImageFetcher(imageCallback(imageView), imageView.getContext()).execute(product.getImageUrl());
        return layout;
    }

	private Callback<Bitmap> imageCallback(final ImageView imageView) {
		return new Callback<Bitmap>() {
			@Override
			public void execute(Bitmap bitmap) {
				if(bitmap == null) return;
				imageView.setImageBitmap(bitmap);
			}
		};
	}


}

