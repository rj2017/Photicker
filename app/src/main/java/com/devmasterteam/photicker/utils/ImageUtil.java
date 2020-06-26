package com.devmasterteam.photicker.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.devmasterteam.photicker.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageUtil {

    //apenas habilita o cache e o retorna como um bitmap
    static Bitmap drawBitmap(RelativeLayout photoContent) {
        photoContent.setDrawingCacheEnabled(true);
        return photoContent.getDrawingCache();
    }

    public static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) {
        //Exchangeable image file format is a standard that specifies
        //Formato de arquivo de imagem intercambiável
        //É um padrão que especifica os formatos para imagens, som e tags auxiliares usados por câmeras digitais, smartphones, etc
        ExifInterface exifInterface;
        try {
            exifInterface = new ExifInterface(selectedImage.getPath());

            //vamos pegar qual a orientação atual da imagem na nossa ImageView
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            //e verificamos qual é a orientação que temos que definir com base na rotação atual
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(img, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(img, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(img, 270);
                default:
                    return img;
            }
        } catch (IOException e) {
            return img;
        }
    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        //cria uma matrix 3x3 para transformações a partir de coordenadas
        Matrix matrix = new Matrix();
        //rotaciona matriz pra o grau que passamos
        matrix.postRotate(degree);
        //cria a nova imagem com base na rotação que definimos, altura e largura da image, rotaçao e nao necessita de filtros
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, false);
        //como não vamos mais utilizar nosso img podemos chamar o método para que o gc saiba que já pode limpa-lo caso necessite
        img.recycle();
        return rotatedImg;
    }

    /**
     * Calcula o tamanho que a imagem deve ser redimensionada
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Largura e altura da imagem
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calcula o maior valor inSampleSize que é uma potência de 2 e mantém altura e largura maior do que o necessário
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * Faz a criação do arquivo de imagem
     */
    public static File createImageFile(Context context) throws IOException {

        // Cria uma imagem com o nome
        String imageFileName = "photicker";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefixo */
                ".jpg",         /* suffixo */
                storageDir      /* diretório */
        );
        // Retorna imagem criada
        return image;
    }

    /**
     * Adiciona todas as imagens a lista
     */
    public static List<Integer> getImagesList() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.st_facial_0);
        images.add(R.drawable.st_facial_1);
        images.add(R.drawable.st_facial_3);
        images.add(R.drawable.st_facial_5);
        images.add(R.drawable.st_facial_4);
        images.add(R.drawable.st_facial_6);
        images.add(R.drawable.st_facial_7);
        images.add(R.drawable.st_facial_8);
        images.add(R.drawable.st_facial_10);
        images.add(R.drawable.st_facial_11);
        images.add(R.drawable.st_facial_12);
        images.add(R.drawable.st_facial_13);
        images.add(R.drawable.st_facial_14);
        images.add(R.drawable.st_misc_1);
        images.add(R.drawable.st_objeto_2);
        images.add(R.drawable.st_objeto_3);
        images.add(R.drawable.st_objeto_4);
        images.add(R.drawable.st_objeto_5);
        images.add(R.drawable.st_animal_0);
        images.add(R.drawable.st_animal_2);
        images.add(R.drawable.st_animal_3);
        images.add(R.drawable.st_animal_4);
        images.add(R.drawable.st_animal_5);
        images.add(R.drawable.st_animal_6);
        images.add(R.drawable.st_animal_7);
        images.add(R.drawable.st_animal_8);
        images.add(R.drawable.st_animal_10);
        images.add(R.drawable.st_animal_11);
        images.add(R.drawable.st_animal_12);
        images.add(R.drawable.st_animal_13);
        images.add(R.drawable.st_animal_14);
        images.add(R.drawable.st_animal_16);
        images.add(R.drawable.st_animal_17);
        images.add(R.drawable.st_animal_18);
        images.add(R.drawable.st_animal_19);
        images.add(R.drawable.st_animal_20);
        images.add(R.drawable.st_animal_21);
        images.add(R.drawable.st_animal_22);
        images.add(R.drawable.st_animal_23);
        images.add(R.drawable.st_animal_24);
        images.add(R.drawable.st_animal_25);
        images.add(R.drawable.st_comida_1);
        images.add(R.drawable.st_comida_2);
        images.add(R.drawable.st_comida_3);
        images.add(R.drawable.st_comida_5);
        images.add(R.drawable.st_comida_6);
        images.add(R.drawable.st_coracao_1);
        images.add(R.drawable.st_coracao_2);
        images.add(R.drawable.st_coracao_3);
        images.add(R.drawable.st_coracao_4);
        images.add(R.drawable.st_coracao_5);
        images.add(R.drawable.st_coracao_6);
        images.add(R.drawable.st_drink_1);
        images.add(R.drawable.st_drink_2);
        images.add(R.drawable.st_drink_3);
        images.add(R.drawable.st_drink_4);
        images.add(R.drawable.st_drink_5);
        images.add(R.drawable.st_drink_6);
        images.add(R.drawable.st_drink_7);
        images.add(R.drawable.st_drink_8);
        images.add(R.drawable.st_drink_9);
        images.add(R.drawable.st_drink_10);
        images.add(R.drawable.st_tatto_1);
        images.add(R.drawable.st_tatto_2);
        images.add(R.drawable.st_tatto_3);
        images.add(R.drawable.st_tatto_4);
        images.add(R.drawable.st_tatto_5);
        images.add(R.drawable.st_tatto_6);
        images.add(R.drawable.st_sticker_2);
        images.add(R.drawable.st_sticker_5);
        images.add(R.drawable.st_sticker_6);
        images.add(R.drawable.st_sticker_7);
        images.add(R.drawable.st_sticker_8);
        images.add(R.drawable.st_sticker_9);
        images.add(R.drawable.st_sticker_11);

        return images;
    }

    /**
     * Decodifica a imagem para o tamanho especificado
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // Primeiro decodifica para verificar as dimensões
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calcula inSampleSize - O valor correspondente para a imagem ser redimensionada
        options.inSampleSize = ImageUtil.calculateInSampleSize(options, reqWidth, reqHeight);

        // Decodifica imagem usando o valor calculado
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * Ação para rotacionar para esquerda
     */
    public static void handleRotateLeft(ImageView mImageSelected) {
        mImageSelected.setRotation(mImageSelected.getRotation() - 5);
    }

    /**
     * Ação para rotacionar para direita
     */
    public static void handleRotateRight(ImageView mImageSelected) {
        mImageSelected.setRotation(mImageSelected.getRotation() + 5);
    }

    /**
     * Ação de ZoomIn - Aumentar imagem
     */
    public static void handleZoomIn(ImageView mImageSelected) {
        if (mImageSelected.getWidth() > 800)
            return;

        ViewGroup.LayoutParams params = mImageSelected.getLayoutParams();
        params.width = (int) (mImageSelected.getWidth() + (mImageSelected.getWidth() * 0.1));
        params.height = (int) (mImageSelected.getHeight() + (mImageSelected.getHeight() * 0.1));
        mImageSelected.setLayoutParams(params);
    }

    /**
     * Ação de ZoomOut - Diminuir imagem
     */
    public static void handleZoomOut(ImageView mImageSelected) {
        if (mImageSelected.getWidth() < 50)
            return;

        ViewGroup.LayoutParams params = mImageSelected.getLayoutParams();
        params.width = (int) (mImageSelected.getWidth() - (mImageSelected.getWidth() * 0.1));
        params.height = (int) (mImageSelected.getHeight() - (mImageSelected.getHeight() * 0.1));
        mImageSelected.setLayoutParams(params);
    }

}
