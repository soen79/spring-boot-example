package springbootapp.machinelearning.classifier.irisflower;

/**
 * Created by User on 1/11/2020.
 */
import weka.classifiers.functions.MultilayerPerceptron;
        import weka.core.Debug;
        import weka.core.Instances;
        import weka.filters.Filter;
        import weka.filters.unsupervised.attribute.Normalize;

/**
 *
 * @author Taha Emara
 * Website: http://www.emaraic.com
 * Email : taha@emaraic.com
 * Created on: Jul 1, 2017
 * Github link: https://github.com/emara-geek/weka-example
 */
public class Test {

    public static final String DATASETPATH = "C:\\Items\\java_workspaces\\gs-spring-boot\\initial\\src\\main\\resources\\data\\iris._data.arff";
    public static final String MODElPATH = "C:\\Items\\java_workspaces\\gs-spring-boot\\initial\\src\\main\\resources\\data\\";

    public static void main(String[] args) throws Exception {

        ModelGenerator mg = new ModelGenerator();

        Instances dataset = mg.loadDataset(DATASETPATH);

        Filter filter = new Normalize();

        // divide dataset to train dataset 80% and test dataset 20%
        int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
        int testSize = dataset.numInstances() - trainSize;

        dataset.randomize(new Debug.Random(1));// if you comment this line the accuracy of the model will be droped from 96.6% to 80%

        //Normalize dataset
        filter.setInputFormat(dataset);
        Instances datasetnor = Filter.useFilter(dataset, filter);

        Instances traindataset = new Instances(datasetnor, 0, trainSize);
        Instances testdataset = new Instances(datasetnor, trainSize, testSize);

        // build classifier with train dataset
        MultilayerPerceptron ann = (MultilayerPerceptron) mg.buildClassifier(traindataset);

        // Evaluate classifier with test dataset
        String evalsummary = mg.evaluateModel(ann, traindataset, testdataset);
        System.out.println("Evaluation: " + evalsummary);

        //Save model
        mg.saveModel(ann, MODElPATH);

        //classifiy a single instance
        ModelClassifier cls = new ModelClassifier();
        String classname =cls.classifiy(Filter.useFilter(cls.createInstance(1.6, 0.2, 0), filter), MODElPATH);
        System.out.println("\n The class name for the instance with petallength = 1.6 and petalwidth =0.2 is  " +classname);

    }

}
