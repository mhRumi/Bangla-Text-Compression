#include<bits/stdc++.h>
#include <sstream>
using namespace std;

vector<int> split (string str)
{

    vector<int> res;
    stringstream ss;

    ss << str;
    string temp;
    int found;
    while (!ss.eof())
    {

        ss >> temp;

        if (stringstream(temp) >> found){
            res.push_back(found);
        }
        temp = "";
    }

    return res;
}

template <typename Iterator>
string decompress(Iterator begin, Iterator end)
{
    // Build the dictionary.
    int dictSize = 256;
    map<int,string> dictionary;
    for (int i = 0; i < 256; i++)
        dictionary[i] = string(1, i);

    string w(1, *begin++);
    string result = w;
    string entry;
    for ( ; begin != end; begin++)
    {
        int k = *begin;

        if (dictionary.count(k))
            entry = dictionary[k];
        else if (k == dictSize)
        {
            entry = w + w[0];
        }
        else
            throw "Bad compressed k";

        result += entry;

        // Add w+entry[0] to the dictionary.
        dictionary[dictSize++] = w + entry[0];

        w = entry;
    }
    return result;
}
int main()
{

    string entry;
    ifstream input;
    input.open("output.txt");
    string text;
    string delimiter = ",";
    string sam;


    if(input.is_open())
    {
        while(input.good())
        {
            getline(input, sam);
            text += sam;
        }
    }

    vector<int> compressed = split (text);
    string decompressed = decompress(compressed.begin(), compressed.end());
    cout << decompressed << endl;

//    for(int i = 0; i<compressed.size(); i++)
//    cout<<compressed[i]<<endl;

    cout<<"Size: "<<compressed.size()<<endl;

}
