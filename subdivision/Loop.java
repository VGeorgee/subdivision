package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Loop {
    Mesh original;
    Mesh newMesh;
    double[] weights;

    Vertex[] newVertices;
    int[] newEdgeMap;
    Face[] newFaces;
    HalfEdge[] newHalfEdges;

    int[] Recalculated;

    public Loop(){
        this(3.0 / 8.0, 3.0 / 8.0, 1.0 / 8.0, 1.0 / 8.0);
    }

    public Loop(double a, double b, double c, double d){
        weights = new double[] { a, b, c, d};
    }


    public Mesh reCalculate(Mesh original){
        this.original = original;
        this.newMesh = new Mesh();

        generateNewVertices();
        setNewVertices();
        splitFaces();
        createNewHalfEdgeInfo();

        newMesh.vertices = (ArrayList<Vertex>) Arrays.asList(newVertices);
        newMesh.faces = (ArrayList<Face>) Arrays.asList(newFaces);
        newMesh.halfEdges = (ArrayList<HalfEdge>) Arrays.asList(newHalfEdges);
        newMesh.initialize();

        return this.newMesh;
    }

    void generateNewVertices(){
        int newVerticesLength = original.vertices.size() + (original.halfEdges.size() / 2);
        newVertices = new Vertex[newVerticesLength];
        newEdgeMap = new int[original.halfEdges.size()];

        for(int i = 0; i < original.halfEdges.size(); i++){
            newEdgeMap[i] = -1;
        }

        int start = original.vertices.size();
        int offset = 0;

        for(int h = 0, n = original.halfEdges.size(); h < n; h++){

            if(newEdgeMap[h] == -1){
                int faceIndex = h / 3;
                System.out.println(faceIndex);
                Vertex a = original.vertices.get(original.faces.get(faceIndex % original.faces.size()).vertexIndices[(h + 1) % 3]).multiply(weights[0]);

                Vertex b =
                original.vertices.get(original.faces.get((original.halfEdges.get(h).pairHalfEdgeIndex / 3) % original.faces.size()).vertexIndices[(original.halfEdges.get(h).pairHalfEdgeIndex + 1) % 3]).multiply(weights[1]);

                //mesh.VerticesArray[mesh.FacesArray[mesh.HalfEdgeArray[h].pairHalfEdgeIndex / 3].v[(mesh.HalfEdgeArray[h].pairHalfEdgeIndex + 1) % 3]]
                Vertex c = original.vertices.get(original.faces.get(faceIndex% original.faces.size()).vertexIndices[(h + 2) % 3]).multiply(weights[2]);
                Vertex d= original.vertices.get(original.faces.get((original.halfEdges.get(h).pairHalfEdgeIndex / 3) % original.faces.size())
                        .vertexIndices[(original.halfEdges.get(h).pairHalfEdgeIndex + 2) % 3]).multiply(weights[3]);

                Vertex newVertex = a.add(b).add(c).add(d);
                newVertex.halfEdgeIndex = -1;

                int index = start + offset;

                newVertices[index] = newVertex;
                newEdgeMap[h] = index;
                newEdgeMap[original.halfEdges.get(h).pairHalfEdgeIndex] = index;
                offset++;
            }

        }
    }

    void setNewVertices(){
        for(int i = 0; i < original.vertices.size(); i++){
            int n = 0;
            int startEdge = original.vertices.get(i).halfEdgeIndex;
            int currentEdge = startEdge;
            Vertex v = new Vertex();
            do{
                currentEdge = original.halfEdges.get(currentEdge).pairHalfEdgeIndex;
               v = v.add(original.vertices.get(original.faces.get(currentEdge / 3).vertexIndices[(currentEdge + 1) % 3]));
               n++;
               currentEdge = 3 * (currentEdge / 3) + ((currentEdge + 2) % 3);
            } while(currentEdge != startEdge);


            double b = (1.0 / 64.0) * (40 - Math.pow(3 + 2 * Math.cos((2 * Math.PI) / n), 2));

            Vertex newVertex = (original.vertices.get(i).multiply(1 - b)).add(v.multiply(b / n));

            newVertices[i] = newVertex;
            newVertices[i].halfEdgeIndex = -1;
        }
    }


    void splitFaces(){
        ArrayList<Face> newFaceslocal = new ArrayList<>();
        for(int i = 0; i < 4 * original.faces.size(); i++){
            newFaceslocal.add(null);
        }

        for(int f = 0, n = original.faces.size(); f < n; f++){
            int f0 = newEdgeMap[3 * f];
            int f1 = newEdgeMap[3 * f + 1];
            int f2 = newEdgeMap[3 * f + 2];


            newFaceslocal.set(4 * f, new Face(f2, original.faces.get(f).vertexIndices[0], f0));
            newFaceslocal.set(4 * f + 1, new Face(f0, original.faces.get(f).vertexIndices[1], f1));
            newFaceslocal.set(4 * f + 2, new Face(f1, original.faces.get(f).vertexIndices[2], f2));
            newFaceslocal.set(4 * f + 3, new Face(f2, f0, f1));

            newVertices[newFaceslocal.get(4 * f).vertexIndices[1]].halfEdgeIndex = f * 12;
            newVertices[newFaceslocal.get(4 * f + 1).vertexIndices[1]].halfEdgeIndex = f * 12 + 3;
            newVertices[newFaceslocal.get(4 * f + 2).vertexIndices[1]].halfEdgeIndex = f * 12 + 6;

            newVertices[newEdgeMap[f * 3]].halfEdgeIndex = f * 12 + 1;
            newVertices[newEdgeMap[f * 3 + 1]].halfEdgeIndex = f * 12 + 4;
            newVertices[newEdgeMap[f * 3 + 2]].halfEdgeIndex = f * 12 + 2;

        }

        newFaces = new Face[newFaceslocal.size()];

        for(int i = 0; i < newFaceslocal.size(); i++){
            newFaces[i] = newFaceslocal.get(i);
        }
    }

    void createNewHalfEdgeInfo(){
        newHalfEdges = new HalfEdge[original.faces.size() * 12];

        for(int f = 0; f < original.faces.size(); f++){
            int b = 12 * f;

            newHalfEdges[b] = new HalfEdge(getIndex(f * 3 + 2, 1));
            newHalfEdges[b + 1] = new HalfEdge(getIndex(f * 3 + 0, 0));
            newHalfEdges[b + 2] = new HalfEdge(b + 9);
            newHalfEdges[b + 3] = new HalfEdge(getIndex((f * 3) + 0, 1)); //e0
            newHalfEdges[b + 4] = new HalfEdge(getIndex((f * 3) + 1, 0)); //e0
            newHalfEdges[b + 5] = new HalfEdge(b + 10);
            newHalfEdges[b + 6] = new HalfEdge(getIndex((f * 3) + 1, 1)); //e1
            newHalfEdges[b + 7] = new HalfEdge(getIndex((f * 3) + 2, 0)); //e2
            newHalfEdges[b + 8] = new HalfEdge(b + 11);
            newHalfEdges[b + 9] = new HalfEdge(b + 2);
            newHalfEdges[b + 10] = new HalfEdge(b + 5);
            newHalfEdges[b + 11] = new HalfEdge(b + 8);
        }
    }

    int getIndex(int index, int offset) {
        int offsets[] = { 3, 1, 6, 4, 0, 7 };

        int op = original.halfEdges.get(index).pairHalfEdgeIndex;

        if (op == -1) {
            return -1;
        }
        int bp = 12 * (op / 3);

        return bp + offsets[2 * (op % 3) + offset];
    }
}
