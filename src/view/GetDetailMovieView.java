package view;

import entity.Movie;
import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.AddToWatchlist.AddToWatchlistState;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistController;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistState;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistViewModel;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieState;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static java.lang.Math.min;

public class GetDetailMovieView extends JPanel implements ActionListener, PropertyChangeListener {
    /** The view name*/
    public final String viewname = "Details";
    /** The viewmodel tha provide information for the view. */
    private final GetDetailMovieViewModel getDetailMovieViewModel;
    /** the controller of add movie to watchlist since the button is shown on get detail of movie view. */
    private final AddToWatchlistController addToWatchlistController;
    /** the controller of delete movie from watchlist since the button is shown on get detail of movie view. */
    private final DeleteWatchlistController deleteWatchlistController;
    /** The label shown the title of the movie being displayed on the screen */
    JLabel movie_title = new JLabel();
    /** The label shown the date of releasing of the movie being displayed on the screen */
    JLabel releasedate = new JLabel();
    /** The label shown the overview of the movie being displayed on the screen */
    JLabel overview = new JLabel();
    /** The label shown the genre of the movie being displayed on the screen */
    JPanel overview_and_poster = new JPanel();
    JLabel genre = new JLabel();
    /** The button to go back to the main menu. */
    JLabel posterLabel = new JLabel();
    JButton backToMainMenu;
    /** The view model that is give information when add to watchlist button is clicked. */
    private final AddToWatchlistViewModel addToWatchlistViewModel;
    /** The view model that is give information when delete movie from watchlist button is clicked. */
    private final DeleteWatchlistViewModel deleteWatchlistViewModel;
    private final ViewManagerModel viewManagerModel;
    /** The view model that give information when go back to the main menu is clicked. */
    private final MainMenuViewModel mainMenuViewModel;


    public GetDetailMovieView(GetDetailMovieViewModel getDetailMovieViewmodel,
                              AddToWatchlistController addToWatchlistcontroller,
                              AddToWatchlistViewModel addToWatchlistViewmodel,
                              DeleteWatchlistController deleteWatchlistController,
                              DeleteWatchlistViewModel deleteWatchlistViewModel,
                              ViewManagerModel viewManagerModel,
                              MainMenuViewModel mainMenuViewModel){
        this.getDetailMovieViewModel = getDetailMovieViewmodel;
        this.addToWatchlistController = addToWatchlistcontroller;
        this.addToWatchlistViewModel = addToWatchlistViewmodel;
        this.deleteWatchlistController = deleteWatchlistController;
        this.deleteWatchlistViewModel = deleteWatchlistViewModel;
        this.addToWatchlistViewModel.addPropertyChangeListener(this);
        this.getDetailMovieViewModel.addPropertyChangeListener(this);

        this.deleteWatchlistViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
        setLayout(null);


        JLabel title = new JLabel("Details");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        JButton addToWatchlist = new JButton(AddToWatchlistViewModel.ADD_WATCH_LIST_BUTTON_LABEL);
        JButton removeFromWatchlist = new JButton(DeleteWatchlistViewModel.DELETE_WATCHLIST_BUTTON_LABEL);
        buttons.add(addToWatchlist);
        buttons.add(removeFromWatchlist);
        backToMainMenu = new JButton(GetWatchListViewmodel.MAIN_MENU_BUTTON_LABEL);
        buttons.add(backToMainMenu);
        backToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backToMainMenu)) {
                    viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        addToWatchlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(addToWatchlist)) {
                    int id = getDetailMovieViewModel.getId();
                    String name = getDetailMovieViewModel.getTitle();
                    String loggedinusername = getDetailMovieViewModel.getLoggedinusername();
                    addToWatchlistController.execute(new Movie(name, id), loggedinusername);
                }
            }
        });
        removeFromWatchlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(removeFromWatchlist)) {
                    int id = getDetailMovieViewModel.getId();
                    String name = getDetailMovieViewModel.getTitle();
                    String loggedinusername = getDetailMovieViewModel.getLoggedinusername();
                    deleteWatchlistController.execute(new Movie(name, id), loggedinusername);
                }
            }
        });
    }

    public void updateView() {
        SwingUtilities.invokeLater(() -> {
            if (movie_title != null) {this.removeAll();}
            setLayout(null);
            this.setAlignmentX(200);
            JLabel title = new JLabel("details");
            // title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel buttons = new JPanel();
            JButton addToWatchlist = new JButton(AddToWatchlistViewModel.ADD_WATCH_LIST_BUTTON_LABEL);
            JButton removeFromWatchlist = new JButton(DeleteWatchlistViewModel.DELETE_WATCHLIST_BUTTON_LABEL);
            buttons.add(addToWatchlist);
            buttons.add(removeFromWatchlist);
            backToMainMenu = new JButton(GetWatchListViewmodel.MAIN_MENU_BUTTON_LABEL);
            buttons.add(backToMainMenu);
            backToMainMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(backToMainMenu)) {
                        viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
            });

            addToWatchlist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(addToWatchlist)) {
                        int id = getDetailMovieViewModel.getId();
                        String name = getDetailMovieViewModel.getTitle();
                        String loggedinusername = getDetailMovieViewModel.getLoggedinusername();
                        addToWatchlistController.execute(new Movie(name, id), loggedinusername);
                    }
                }
            });
            removeFromWatchlist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(removeFromWatchlist)) {
                        int id = getDetailMovieViewModel.getId();
                        String name = getDetailMovieViewModel.getTitle();
                        String loggedinusername = getDetailMovieViewModel.getLoggedinusername();
                        deleteWatchlistController.execute(new Movie(name, id), loggedinusername);
                    }
                }
            });
            Font font20 = new Font("Arial", Font.PLAIN, 20);
            //SwingUtilities.invokeLater(() -> {
                JTextArea descriptionArea = new JTextArea();
                descriptionArea.setText("OVERVIEW:" + "\n" +getDetailMovieViewModel.getOverview());
                descriptionArea.setEditable(false);
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                descriptionArea.setFont(font20);
                descriptionArea.setAlignmentX(200);
                descriptionArea.setMargin(new Insets(100, 100, 0, 100));
                // this.add(descriptionArea);

            //});

            movie_title.setText(getDetailMovieViewModel.getTitle());
            Font font60 = new Font("Arial", Font.PLAIN, 60);
            movie_title.setFont(font60);
            String genre_text = "Genres:  ";
            if (getDetailMovieViewModel.getGenre() != null) {
                List<String> genre_list = getDetailMovieViewModel.getGenre();
                for (int i = 0; i < genre_list.size(); i++){
                    genre_text = genre_text + "   " + genre_list.get(i);
                }
            }
            genre.setText(genre_text);
            genre.setFont(font20);
            // this.setAlignmentX(200);
            movie_title.setAlignmentX(200);
            genre.setAlignmentX(200);
            title.setFont(new Font("Arial", Font.PLAIN, 40));
            title.setAlignmentX(200);
            releasedate.setText("Released at  " + getDetailMovieViewModel.getReleaseDate().toString());
            releasedate.setFont(font20);
            releasedate.setAlignmentX(200);


            String url = "https://image.tmdb.org/t/p/w1280" + getDetailMovieViewModel.getPoster_path();
            //ImageIcon image = new ImageIcon(url);
            System.out.println(url);
            try {
                BufferedImage image2 = ImageIO.read(new URL(url));
                Image image = image2.getScaledInstance(320, 480, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(image);
                posterLabel.setSize(640, 960);
                posterLabel.setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();}
            posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
            overview_and_poster.setLayout(new BoxLayout(overview_and_poster, BoxLayout.X_AXIS));
            overview_and_poster.add(posterLabel, BorderLayout.CENTER);
            overview_and_poster.add(descriptionArea, BorderLayout.WEST);



            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(buttons);
            this.add(title);
            this.add(movie_title, BorderLayout.WEST);
            this.add(genre, BorderLayout.WEST);
            this.add(releasedate, BorderLayout.WEST);
            this.add(overview_and_poster);
            // this.add(posterLabel);
        });
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof GetDetailMovieState){
            GetDetailMovieState getDetailMovieState = (GetDetailMovieState) evt.getNewValue();
            if (getDetailMovieState.getError() != null){
                JOptionPane.showMessageDialog(this, getDetailMovieState.getError());
            } else {
                switch (evt.getPropertyName()) {
                    case "state":
                        updateView();
                        break;
                    case "error":
                        break;
                }
            }
        }
        else if (evt.getNewValue() instanceof AddToWatchlistState) {
            AddToWatchlistState state = (AddToWatchlistState) evt.getNewValue();
            if (state.getMovieExistError() != null) {
                JOptionPane.showMessageDialog(this, state.getMovieExistError());
            } else {
                JOptionPane.showMessageDialog(this, state.getMessage());
            }
        }

        else if (evt.getNewValue() instanceof DeleteWatchlistState) {
            DeleteWatchlistState state = (DeleteWatchlistState) evt.getNewValue();
            if (state.getMovieExistError() != null) {
                JOptionPane.showMessageDialog(this, state.getMovieExistError());
            } else {
                JOptionPane.showMessageDialog(this, state.getMessage());
            }
        }
    }
}


