package io.ylab.intensive.lesson04.movie;

public class Packer {
    public Movie getMovie(String[] fields, int linesCounter) throws FieldNotFoundException {
        Movie movie = new Movie();
        movie.setTitle(fields[2]);

        try {
            if (fields[0].equals("")) {
                movie.setYear(0);
                throw new FieldNotFoundException("Line " + linesCounter
                        + " " + fields[2] + " - year not found");
            } else {
                movie.setYear(Integer.parseInt(fields[0]));
            }

            if (fields[1].equals("")) {
                movie.setLength(0);
                throw new FieldNotFoundException("Line " + linesCounter
                        + " " + fields[2] + " - length not found");
            } else {
                movie.setLength(Integer.parseInt(fields[1]));
            }

            if (fields[3].equals("")) {
                movie.setSubject("-");
                throw new FieldNotFoundException("Line " + linesCounter
                        + " " + fields[2] + " - subject not found");
            } else {
                movie.setSubject(fields[3]);
            }

            if (fields[4].equals("")) {
                movie.setActors("-");
                throw new FieldNotFoundException("Line " + linesCounter
                        + " " + fields[2] + " - actors not found");
            } else {
                movie.setActors(fields[4]);
            }

            if (fields[5].equals("")) {
                movie.setActress("-");
                throw new FieldNotFoundException("Line " + linesCounter
                        + " " + fields[2] + " - actress not found");
            } else {
                movie.setActress(fields[5]);
            }

            if (fields[6].equals("")) {
                movie.setDirector("-");
                throw new FieldNotFoundException("Line " + linesCounter
                        + " " + fields[2] + " - director not found");
            } else {
                movie.setDirector(fields[6]);
            }

            if (fields[7].equals("")) {
                movie.setPopularity(0);
                throw new FieldNotFoundException("Line " + linesCounter
                        + " " + fields[2] + " - popularity not found");
            } else {
                movie.setPopularity(Integer.parseInt(fields[7]));
            }

            if (fields[8].equals("")) {
                movie.setAwards(false);
                throw new FieldNotFoundException("Line " + linesCounter
                        + " " + fields[2] + " - awards not found");
            } else {
                movie.setAwards(fields[8].equals("Yes"));
            }
        } catch (FieldNotFoundException e) {
            System.out.println(e);
        }

        return movie;
    }
}
