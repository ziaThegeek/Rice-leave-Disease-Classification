package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class results extends AppCompatActivity {
    TextView symptoms,damage1,disease,treatment1;
    Intent intent;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
//        getSupportActionBar().hide();
        symptoms=findViewById(R.id.symptoms);
        damage1=findViewById(R.id.damage);
        treatment1=findViewById(R.id.treatment
        );
        disease=findViewById(R.id.disease);
        intent=getIntent();
        index=intent.getIntExtra("index",1);
        String[] diseases={"brown spot","healthy","hispa","leaf blast"};
        String[] symtoms={"Brown Spot is called as sesame leaf spot or Helminthosporiose or fungal blight\n" +
                "The fungus attacks the crop from seedling in nursery to milk stage in main field.\n" +
                "The disease appears first as minute brown dots, later becoming cylindrical or oval to circular.(resemble sesame seed)\n" +
                "Spots measures 0.5 to 2.0mm in breadth - coalesce to form large patches.\n" +
                "Then several spots coalesce and the leaf dries up.\n" +
                "Infection also occurs on panicle, neck with brown colour appearance\n" +
                "Seeds also infected (black or brown spots on glumes spots are covered by olivaceous velvety growth)\n" +
                "The seedlings die and affected nurseries can be often recognized from a distance by their brownish scorched appearance.\n" +
                "Dark brown or black spots also appear on glumes.\n" +
                "The infection of the seed causes failure of seed germination, seedling mortality and reduces the grain quality and weight.\n" +
                "50% yield reduction in severe cases .\n","Proper Crop color,height  with consistent growth and having a sufficient quantity of nutrients like K,N and P " +
                "are some  of main  indications of a healthy rice plant","The mining of the grubs will be clearly seen on the leaves.\n" +
                "Scraping of the upper surface of the leaf blade leaving only the lower epidermis as white streaks parallel to the midrib.\n" +
                "Tunneling of larvae through leaf tissue causes irregular translucent white patches that are parallel to the leaf veins.\n" +
                "Damaged leaves wither off.\n" +
                "Rice field appears burnt when severely infested.","Blast symptoms can occur on leaves, leaf collars, nodes and panicles. Leaf spots are typically elliptical (football shaped), with gray-white centers and brown to red-brown margins (Figure 1). Fully developed leaf lesions are approximately 0.4 to 0.7 inch long and 0.1 to 0.2 inch wide. Both the shape and the color vary depending on the environment, age of the lesion and rice variety. Lesions on leaf sheaths, which rarely develop, resemble those on leaves"};
        String[] damage={"Infected seedlings have small, circular, yellow brown or brown lesions that may girdle the coleoptile and distort primary and secondary leaves.\n" +
                "Starting at tillering stage, lesions can be observed on the leaves. They are initially small, circular, and dark brown to purple-brown.\n" +
                "Fully developed lesions are circular to oval with a light brown to gray center, surrounded by a reddish brown margin caused by the toxin produced by the fungi.","No damage to the crop","scraping of the upper surface of the leaf blade leaving only the lower epidermis as white streaks parallel to the midrib\n" +
                "irregular translucent white patches that are parallel to the leaf veins caused by tunneling of larvae through leaf tissue causes\n" +
                "withering of damaged leaves\n" +
                "whitish and membranous leaves","The most serious damage occurs when the fungus attacks nodes just below the head (Figure 2). The stems often break at the diseased node. This stage of the disease is referred to as “rotten neck.” Disease in the node prevents the flow of water and nutrients to the kernels and they will stop developing. Heads of plants damaged in this way may be completely blank (Figure 3) to nearly normal, depending on the stage of head development when infection occurs. The poorly developed grain usually breaks up badly in milling, reducing quality."};
        String[] treatment={"Seed treatment with captan, thiram, chitosan, carbendazim, or mancozeb has been found to reduce seedling infection. Seed treatment with tricyclazole followed by spraying of mancozeb + tricyclazole at tillering and late booting stages gave good control of the disease.","make sure of providing necessary ingredients and proper irrigation in order to get expected yield and better quality","Avoid over fertilizing the field.\n" +
                "Close plant spacing results in greater leaf densities that can tolerate higher hispa numbers.\n" +
                "Leaf tip containing blotch mines should be destroyed.\n" +
                "Manual collection and killing of beetles – hand nets.\n" +
                "To prevent egg laying of the pests, the shoot tips can be cut","Planting resistant varieties against the rice blast is the most practical and economical way of controlling rice blast.\n" +
                "Use of Tolerant varieties (CO 47, CO 50, ADT 36,ADT 37,ASD 16,ASD 20,ADT 39,ASD 19,TPS 3,White ponni,ADT 44,BPT 5204,CORH , Palghuna, Swarnamukhi, Swathi, Prabhat, IR - 64, , IR - 36, Jaya)\n" +
                " Avoid excess N - fertilizer application\n" +
                "Apply nitrogen in three split doses.\n" +
                "  Remove weed hosts from bunds"};
        disease.setText(diseases[index]);
        symptoms.setText(symtoms[index]);
        damage1.setText(damage[index]);
        treatment1.setText(treatment[index]);



    }
}